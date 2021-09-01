package ir.madreseplus.ui.view.practice;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import ir.madreseplus.Injection;
import ir.madreseplus.R;
import ir.madreseplus.ViewModelProviderFactory;
import ir.madreseplus.data.model.enums.EventStateEnum;
import ir.madreseplus.data.model.req.Practice;
import ir.madreseplus.data.model.req.Task;
import ir.madreseplus.databinding.ActivityPracticeBinding;
import ir.madreseplus.ui.base.BaseActivity;
import ir.madreseplus.ui.base.SimpleDialog;
import ir.madreseplus.ui.view.dialogs.InputPracticeDialog;
import ir.madreseplus.utilities.Config;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class PracticeActivity extends BaseActivity<ActivityPracticeBinding> implements PracticeActivityNavigator {

    private PracticeViewModel practiceViewModel;

    private Integer id, wrongPractices, correctPractices, notAnsweredPractices;

    @Override
    protected int layoutRes() {
        return R.layout.activity_practice;
    }

    @Override
    protected void setBinding() {
        binding = ActivityPracticeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProviderFactory factory = Injection.providerFactory(this);
        practiceViewModel = ViewModelProviders.of(this, factory).get(PracticeViewModel.class);
        practiceViewModel.setNavigator(this);
        practiceViewModel.getTasks().observe(this, this::getPracticeById);

        showOrHide();
        setListeners();

        //get from intent
        id = getIntent().getIntExtra(Config.ID_KEY, 0);
        practiceViewModel.getPracticeById(id);
    }

    private void getPracticeById(Task task) {


        binding.txtViewSuggestedTime.setText(task.getSuggestedTime());
        binding.txtViewTitle.setText(task.getTitle());
        String studyHeadline = task.getChapterName() + " | " + task.getPartName();
        binding.txtViewStudyHeadline.setText(studyHeadline);
        binding.txtViewSuggestedBook.setText(task.getBookName());
        binding.txtViewDesc.setText(Html.fromHtml(task.getAdvDescription()));
        binding.txtViewDesc.setMovementMethod(LinkMovementMethod.getInstance());

        if (task.getSituation().equals(EventStateEnum.DONE)) {
            binding.txtViewCorrect.setText(String.valueOf(task.getCorrect()));
            binding.txtViewWrong.setText(String.valueOf(task.getWrong()));
            binding.txtViewNotAnswered.setText(String.valueOf(task.getNoAnswer()));
            binding.imgViewDonePractices.setVisibility(GONE);

            binding.cvPercent.setVisibility(VISIBLE);
            binding.txtViewPercent.setText(String.valueOf(task.getPercent()));
            binding.imgViewDonePractices.setVisibility(GONE);

            binding.cvCorrect.setOnClickListener(null);
            binding.cvNotAnswered.setOnClickListener(null);
            binding.cvWrong.setOnClickListener(null);
        }


        showOrHide();
    }


    private void setListeners() {
        binding.imgViewBack.setOnClickListener(v -> finish());

        binding.imgViewDonePractices.setOnClickListener(v -> {
            if (notAnsweredPractices == null) {
                showMsgToast("تعداد تست های نزده را وارد کنید");
                return;
            }
            if (correctPractices == null) {
                showMsgToast("تعداد تست های صحیح را وارد کنید");
                return;
            }
            if (wrongPractices == null) {
                showMsgToast("تعداد تست های غلط را وارد کنید");
                return;
            }
            showDoneDialog();
        });

        binding.cvWrong.setOnClickListener(v -> {
            showInputDialog(InputPracticeDialog.PAType.WRONG);
        });

        binding.cvCorrect.setOnClickListener(v -> {
            showInputDialog(InputPracticeDialog.PAType.CORRECT);
        });

        binding.cvNotAnswered.setOnClickListener(v -> {
            showInputDialog(InputPracticeDialog.PAType.NOTANSWERED);
        });
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
            practiceViewModel.setPracticeResult(id, correctPractices, wrongPractices, notAnsweredPractices);
        });
    }

    private void showMsgToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private void showInputDialog(InputPracticeDialog.PAType paType) {
        InputPracticeDialog dialog = new InputPracticeDialog(this, paType);
        dialog.show();
        dialog.setCancelable(true);
        dialog.setDialogListener((dialog1, view, paType1) -> {
            switch (paType) {
                case WRONG:
                    wrongPractices = dialog1.getInputText();
                    binding.txtViewWrong.setText(String.valueOf(dialog1.getInputText()));
                    checkPercent();
                    break;
                case CORRECT:
                    correctPractices = dialog1.getInputText();
                    binding.txtViewCorrect.setText(String.valueOf(dialog1.getInputText()));
                    checkPercent();
                    break;
                case NOTANSWERED:
                    notAnsweredPractices = dialog1.getInputText();
                    binding.txtViewNotAnswered.setText(String.valueOf(dialog1.getInputText()));
                    checkPercent();
                    break;
            }
        });
    }

    private void checkPercent() {
        if (notAnsweredPractices != null &&
                wrongPractices != null &&
                correctPractices != null) {
            float all = correctPractices + wrongPractices + notAnsweredPractices;
            float percent = ((3 * correctPractices) - wrongPractices) / (all * 3) * 100;
            String pr = String.format("%.2f", percent);
            binding.txtViewPercent.setText("%" + pr);
            binding.cvPercent.setVisibility(VISIBLE);
        }
    }


    private synchronized void showOrHide() {
        binding.progressBar.setVisibility(binding.progressBar.getVisibility() == GONE ? VISIBLE : GONE);
        binding.container1.setVisibility(binding.container1.getVisibility() == VISIBLE ? GONE : VISIBLE);
        binding.container2.setVisibility(binding.container2.getVisibility() == VISIBLE ? GONE : VISIBLE);
        binding.container3.setVisibility(binding.container3.getVisibility() == VISIBLE ? GONE : VISIBLE);
        binding.container4.setVisibility(binding.container4.getVisibility() == VISIBLE ? GONE : VISIBLE);
        binding.container5.setVisibility(binding.container5.getVisibility() == VISIBLE ? GONE : VISIBLE);
        binding.rlPracticeContainerActions.setVisibility(binding.rlPracticeContainerActions.getVisibility() == VISIBLE ? GONE : VISIBLE);
    }

    @Override
    public void error(Throwable throwable) {
        Toast.makeText(PracticeActivity.this, "", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void practiceDone() {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
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
