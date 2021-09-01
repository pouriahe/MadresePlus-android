package ir.madreseplus.ui.view.schedule.dailyreport;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import ir.madreseplus.Injection;
import ir.madreseplus.R;
import ir.madreseplus.ViewModelProviderFactory;
import ir.madreseplus.data.model.enums.MoodLevelEnum;
import ir.madreseplus.data.model.req.DailyReportReq;
import ir.madreseplus.data.model.res.DailyReportRes;
import ir.madreseplus.utilities.SimpleTextWatcher;
import ir.madreseplus.utilities.StringUtil;


public class DailyReportDialog extends DialogFragment implements DailyReportNavigator {
    private static final String TAG = DailyReportDialog.class.getSimpleName();

    private DailyReportViewModel dailyReportViewModel;
    private DailyReportReq dailyReportReq;

    private View view;
    private RadioGroup radioGroup;
    private EditText editText;
    private TextView numberTextView;
    private ProgressBar progressBar;
    private Button submitButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProviderFactory factory = Injection.providerFactory(getContext());
        dailyReportViewModel = ViewModelProviders.of(this, factory).get(DailyReportViewModel.class);
        dailyReportViewModel.setNavigator(this);

        dailyReportReq = new DailyReportReq(); // initial the model
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.ReportDialog);

        initView();
        setListener();
        dailyReportViewModel.getDailyReportState(); // get the state of report

        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setListener() {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rd_veryBad:
                    dailyReportReq.setMoodLevel(MoodLevelEnum.VERY_BAD);
                    break;
                case R.id.rd_bad:
                    dailyReportReq.setMoodLevel(MoodLevelEnum.BAD);
                    break;
                case R.id.rd_normal:
                    dailyReportReq.setMoodLevel(MoodLevelEnum.NORMAL);
                    break;
                case R.id.rd_good:
                    dailyReportReq.setMoodLevel(MoodLevelEnum.GOOD);
                    break;
                case R.id.rd_veryGood:
                    dailyReportReq.setMoodLevel(MoodLevelEnum.VERY_GOOD);
                    break;
            }
        });

        submitButton.setOnClickListener(v -> {
            dailyReportReq.setMoodText(editText.getText().toString());
            if (valid()) {
                dailyReportViewModel.submitDailyReport(dailyReportReq);
            }
        });
    }

    private boolean valid() {
        if (dailyReportReq.getMoodLevel() == null) {
            Toast.makeText(getContext(), "لطفا نظرت رو بنویس", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initView() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_report, null);
        radioGroup = view.findViewById(R.id.categoryReportRadioGroup);
        editText = view.findViewById(R.id.et_report);
        progressBar = view.findViewById(R.id.progressBar);
        submitButton = view.findViewById(R.id.btn_submit);
        numberTextView = view.findViewById(R.id.txtView_character_number);
        editText.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (StringUtil.hasText(s)) {
                    numberTextView.setText(String.valueOf(s.length()));
                }
            }
        });
    }


    public static DailyReportDialog newInstance() {
        DailyReportDialog fragment = new DailyReportDialog();
        return fragment;
    }

    @Override
    public void error(Throwable throwable) {
        Log.e(TAG, "error: " + throwable.getMessage());
    }

    @Override
    public void dailyReportSubmitRes(DailyReportRes dailyReportRes) {
        this.dismiss();
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_daily_report_submit_success);
        dialog.show();
    }

    @Override
    public void dailyReportStateRes(DailyReportRes dailyReport) {
        progressBar.setVisibility(View.GONE);
        if (!dailyReport.getCan_send()) {
            submitButton.setEnabled(false);
            submitButton.setText("قبلا ثبت کردی");
            radioGroup.setOnCheckedChangeListener(null);
            editText.setEnabled(false);
            editText.setText(dailyReport.getMoodText());
            checkRadioState(dailyReport.getMoodLevel());
        }
    }

    private void checkRadioState(MoodLevelEnum moodLevel) {
        switch (moodLevel) {
            case VERY_BAD:
                ((RadioButton) view.findViewById(R.id.rd_veryBad)).setChecked(true);
                break;
            case BAD:
                ((RadioButton) view.findViewById(R.id.rd_bad)).setChecked(true);
                break;
            case NORMAL:
                ((RadioButton) view.findViewById(R.id.rd_normal)).setChecked(true);
                break;
            case GOOD:
                ((RadioButton) view.findViewById(R.id.rd_good)).setChecked(true);
                break;
            case VERY_GOOD:
                ((RadioButton) view.findViewById(R.id.rd_veryGood)).setChecked(true);
                break;
        }
    }
}
