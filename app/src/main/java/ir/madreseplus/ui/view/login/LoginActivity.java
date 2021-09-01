package ir.madreseplus.ui.view.login;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.orhanobut.hawk.Hawk;

import java.util.List;
import java.util.regex.Pattern;

import ir.madreseplus.Injection;
import ir.madreseplus.R;
import ir.madreseplus.ViewModelProviderFactory;
import ir.madreseplus.data.model.req.Student;
import ir.madreseplus.data.model.req.User;
import ir.madreseplus.data.model.res.ConfirmRes;
import ir.madreseplus.databinding.ActivityLoginBinding;
import ir.madreseplus.ui.base.BaseActivity;
import ir.madreseplus.ui.view.MainActivity;
import ir.madreseplus.ui.view.dialogs.NotRegisteredBottomSheet;
import ir.madreseplus.ui.view.login.complete.CompleteRegisterActivity;
import ir.madreseplus.utilities.Helper;
import ir.madreseplus.utilities.LoadingDialog;
import ir.madreseplus.utilities.SimpleTextWatcher;
import ir.madreseplus.utilities.Validations;
import retrofit2.Response;

import static ir.madreseplus.utilities.Config.COMPLEMENT_REGISTER;
import static ir.madreseplus.utilities.Config.REGISTER_CONFIRM_CODE;
import static ir.madreseplus.utilities.Config.CONFIRM_CODE_ERROR;
import static ir.madreseplus.utilities.Config.REGISTERED;
import static ir.madreseplus.utilities.Config.TOKEN_KEY;
import static ir.madreseplus.utilities.Config.USER_GENDER_KEY;
import static ir.madreseplus.utilities.Config.USER_MOBILE_KEY;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements LoginNavigator<User> {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private final String NUMBER_PATTERN = "09\\d{9}";
    private final String CODE_PATTERN = "\\d{5}";
    private static int state = 0;
    private int timeToResend = 120000;
    private String sessionId = "";
    private CountDownTimer countDownTimer;
    private LoadingDialog loadingDialog;

    private String phone;

    private LoginViewModel loginViewModel;

    @Override
    protected int layoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void setBinding() {
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProviderFactory factory = Injection.providerFactory(this);
        loginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);
        loginViewModel.setNavigator(this);

        loadingDialog = new LoadingDialog(this);

        init();
        setListener();
        binding.btnLogin.setOnClickListener(v -> onLoginClick());
    }

    private void setListener() {
        binding.txtViewPrivacy.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://madreseplus.com/privacy")));
        });
        binding.txtViewTerms.setOnClickListener(v ->{
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://madreseplus.com/terms")));
        });
    }

    private void init() {
        binding.btnLogin.setEnabled(false);
        binding.etPhone.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                if (s != null) {
                    binding.btnLogin.setEnabled(
                            Pattern.matches(NUMBER_PATTERN, Helper.toEnglish(s.toString()))
                    );
                }
            }
        });
        binding.etConfirmCode.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                if (s != null) {
                    binding.btnLogin.setEnabled(Pattern.matches(CODE_PATTERN,
                            Helper.toEnglish(s.toString())));
                }
            }
        });
        SpannableString termsSpannableString = new SpannableString(binding.txtViewTerms.getText());
        ForegroundColorSpan backgroundSpan = new ForegroundColorSpan(Color.BLUE);
        UnderlineSpan underlineSpan = new UnderlineSpan();
        termsSpannableString.setSpan(backgroundSpan, 0, termsSpannableString.length() - 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        termsSpannableString.setSpan(underlineSpan, 0, termsSpannableString.length() - 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.txtViewTerms.setText(termsSpannableString);

        SpannableString privacySpannableString = new SpannableString(binding.txtViewPrivacy.getText());
        privacySpannableString.setSpan(backgroundSpan, 0, privacySpannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        privacySpannableString.setSpan(underlineSpan, 0, privacySpannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.txtViewPrivacy.setText(privacySpannableString);

    }

    public void onLoginClick() {
        binding.btnLogin.setEnabled(false);
        switch (state) {
            case 0:
                progressLogin();
                break;
            case 1:
                progressPassword();
                break;
            case 2:
                progressConfirmCode();
                break;
        }
    }

    private void progressConfirmCode() {
        loadingDialog.showDialog();
        phone = binding.etPhone.getText().toString().trim();
        String confirm = binding.etConfirmCode.getText().toString().trim();
        User user = new User();
        user.setPhone(phone);
        user.setCode(confirm);
        loginViewModel.confirmCode(user, sessionId);
    }

    private void progressPassword() {
        loadingDialog.showDialog();
        phone = binding.etPhone.getText().toString().trim();
        String pass = binding.etPassword.getText().toString().trim();
        User user = new User();
        user.setPhone(phone);
        user.setPassword(pass);
        loginViewModel.password(user);
    }

    private void progressLogin() {
        loadingDialog.showDialog();
        if (!new Validations().phoneNumber(binding.etPhone.getText().toString())) {
            Toast.makeText(this, R.string.wrong_phone_number, Toast.LENGTH_LONG).show();
            return;
        }
        phone = binding.etPhone.getText().toString().trim();
        String pass = binding.etPassword.getText().toString().trim();
        User user = new User();
        user.setPhone(phone);
        user.setPassword(pass);
        loginViewModel.login(user);
    }

    @Override
    public void error(Throwable throwable) {
        loadingDialog.hideDialog();
        binding.btnLogin.setEnabled(true);
        Log.e(TAG, "error: " + throwable.getMessage());
    }

    @Override
        public void login(User user) {
        loadingDialog.hideDialog();
        binding.btnLogin.setEnabled(true);
        if (user.getNext_state() == REGISTERED) {
            state = 1;
            binding.inputLayerPassword.setVisibility(View.VISIBLE);
            binding.btnLogin.setText(R.string.str_login_to_mp);
        } else if (user.getNext_state() == REGISTER_CONFIRM_CODE) {
            state = 2;
            binding.btnLogin.setText(getString(R.string.submit));
            binding.lnConfirmCode.setVisibility(View.VISIBLE);
            startTimer();
        } else if (user.getNext_state() == COMPLEMENT_REGISTER) {
            finish();
            startActivity(new Intent(LoginActivity.this, CompleteRegisterActivity.class));
        } else {
            NotRegisteredBottomSheet bottomSheetFragment = NotRegisteredBottomSheet.newInstance();
            bottomSheetFragment.show(getSupportFragmentManager(), NotRegisteredBottomSheet.class.getSimpleName());
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeToResend, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                String s = String.format("%02d:%02d", minutes, seconds);
                binding.txtTimer.setText(s);
            }

            public void onFinish() {
                try {

                    binding.lnTimer.setVisibility(View.GONE);
                    binding.txtResend.setVisibility(View.VISIBLE);
                    binding.txtResend.setOnClickListener((View.OnClickListener) v -> {
                        binding.txtResend.setVisibility(View.GONE);
                        binding.lnTimer.setVisibility(View.VISIBLE);
                        progressLogin();
                    });
                } catch (Exception e) {
                }
            }

        }.start();
    }

    @Override
    public void password(User user) {
        loadingDialog.hideDialog();
        binding.btnLogin.setEnabled(true);
        if (user.getToken() != null) {
            Hawk.put(TOKEN_KEY, user.getToken());
            loginViewModel.getProfile();
        } else {
            binding.inputLayerPassword.setError("پسورد اشتباهه!!!");
        }
    }

    @Override
    public void profile(List<Student> students) {
        binding.btnLogin.setEnabled(true);
        Hawk.put(USER_MOBILE_KEY, phone); // save user mobile
        Hawk.put(USER_GENDER_KEY, students.get(0).getUserProfile().getGender());

        ir.madreseplus.data.model.entity.Student student = new ir.madreseplus.data.model.entity.Student();
        student.setGrade(students.get(0).getGetGradeDisplay());
        student.setFieldStudy(students.get(0).getGetFieldDisplay());
        student.setLastName(students.get(0).getUserProfile().getLastName());
        student.setFirstName(students.get(0).getUserProfile().getFirstName());
        student.setSchoolName(students.get(0).getSchoolName());
        student.setGenderTypeEnum(students.get(0).getUserProfile().getGender());

        loginViewModel.insertStudent(student);
        finish();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void confirmResult(ConfirmRes confirmRes) {
        loadingDialog.hideDialog();
        if (confirmRes.getStatus() == 200) {
            Hawk.put(TOKEN_KEY, confirmRes.getToken());
            Hawk.put(USER_MOBILE_KEY, phone);
            state = 0; // set to initialize mode
            finish();
            startActivity(new Intent(LoginActivity.this, CompleteRegisterActivity.class));
        } else if (confirmRes.getStatus() == CONFIRM_CODE_ERROR) {
            binding.inputLayerConfirmCode.setErrorEnabled(true);
            Toast.makeText(this, confirmRes.getError(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        if (countDownTimer != null) countDownTimer.cancel();
        super.onDestroy();
    }
}
