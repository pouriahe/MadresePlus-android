package ir.madreseplus.ui.view.task;

import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import ir.madreseplus.Injection;
import ir.madreseplus.R;
import ir.madreseplus.ViewModelProviderFactory;
import ir.madreseplus.data.model.enums.EventStateEnum;
import ir.madreseplus.data.model.enums.EventTypeEnum;
import ir.madreseplus.data.model.req.Task;
import ir.madreseplus.databinding.ActivityTaskBinding;
import ir.madreseplus.ui.base.BaseActivity;
import ir.madreseplus.ui.base.SimpleDialog;
import ir.madreseplus.ui.view.usage.UsageActivity;
import ir.madreseplus.utilities.Config;
import ir.madreseplus.utilities.PicassoImageGetter;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class TaskActivity extends BaseActivity<ActivityTaskBinding> implements TaskActivityNavigator {

    private TaskViewModel taskViewModel;

    private int id;

    @Override
    protected int layoutRes() {
        return R.layout.activity_task;
    }

    @Override
    protected void setBinding() {
        binding = ActivityTaskBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProviderFactory factory = Injection.providerFactory(this);
        taskViewModel = ViewModelProviders.of(this, factory).get(TaskViewModel.class);
        taskViewModel.setNavigator(this);
        taskViewModel.getTasks().observe(this, this::getEventById);

        showOrHide();
        setListeners();

        //get from intent
        id = getIntent().getIntExtra(Config.ID_KEY, 0);
        taskViewModel.getEventById(id);
    }

    private void setListeners() {
        binding.imgViewBack.setOnClickListener(v -> finish());

        binding.rlDoneItContainer.setOnClickListener(v -> {
            showDoneDialog();
        });
    }

    private synchronized void showOrHide() {
        binding.progressBar.setVisibility(binding.progressBar.getVisibility() == GONE ? VISIBLE : GONE);
        binding.container1.setVisibility(binding.container1.getVisibility() == VISIBLE ? GONE : VISIBLE);
        binding.container2.setVisibility(binding.container2.getVisibility() == VISIBLE ? GONE : VISIBLE);
        binding.container3.setVisibility(binding.container3.getVisibility() == VISIBLE ? GONE : VISIBLE);
        binding.container4.setVisibility(binding.container4.getVisibility() == VISIBLE ? GONE : VISIBLE);
        binding.container5.setVisibility(binding.container5.getVisibility() == VISIBLE ? GONE : VISIBLE);
        binding.rlDoneItContainer.setVisibility(binding.rlDoneItContainer.getVisibility() == VISIBLE ? GONE : VISIBLE);
    }

    private void showDoneDialog() {
        SimpleDialog dialog = new SimpleDialog(this);
        dialog.setMessage(getString(R.string.str_done_it));
        dialog.setImageRes(R.drawable.logo);
        dialog.setButtonTxt(getString(R.string.str_yes));
        dialog.setCancelButtonTxt(getString(R.string.str_no));
        dialog.show();
        dialog.setCancelable(true);
        dialog.setDialogListener((dialog1, view) -> {
            taskViewModel.setTask(id);
        });
    }


    @Override
    public void error(Throwable throwable) {
        Toast.makeText(TaskActivity.this, getString(R.string.rest_error), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void taskDone() {
        finish();
    }

    public void getEventById(Task task) {


        binding.txtViewSuggestedTime.setText(task.getSuggestedTime());
        binding.txtViewTitle.setText(task.getTitle());
        String studyHeadline = task.getChapterName() + " | " + task.getPartName();
        binding.txtViewStudyHeadline.setText(studyHeadline);
        binding.txtViewSuggestedBook.setText(task.getBookName());

        PicassoImageGetter imageGetter = new PicassoImageGetter(binding.txtViewDesc);
        Spannable html;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            html = (Spannable) Html.fromHtml(task.getAdvDescription(), Html.FROM_HTML_MODE_LEGACY, imageGetter, null);
        } else {
            html = (Spannable) Html.fromHtml(task.getAdvDescription(), imageGetter, null);
        }
//            binding.txtViewUsage.setText(html);
        binding.txtViewDesc.setText(html);
        binding.txtViewDesc.setMovementMethod(LinkMovementMethod.getInstance());

        if (task.getKind().equals(EventTypeEnum.REVIEW)) {
            binding.txtViewTaskContext.setText("مرور");
        }

        if (task.getSituation().equals(EventStateEnum.DONE)) {
            binding.txtViewDoIt.setText(getResources().getString(R.string.str_done));
            binding.imgViewSituation.setVisibility(GONE);
            binding.rlDoneItContainer.setEnabled(false);
        }

        showOrHide();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
