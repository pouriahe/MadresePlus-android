package ir.madreseplus.ui.view.login.complete;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import java.util.List;

import ir.madreseplus.Injection;
import ir.madreseplus.R;
import ir.madreseplus.ViewModelProviderFactory;
import ir.madreseplus.data.model.req.Student;
import ir.madreseplus.data.model.req.User;
import ir.madreseplus.data.model.res.ComplementRes;
import ir.madreseplus.databinding.ActivityCompleteRegisterBinding;
import ir.madreseplus.ui.base.BaseActivity;
import ir.madreseplus.ui.view.MainActivity;
import ir.madreseplus.ui.view.login.LoginActivity;
import ir.madreseplus.ui.view.login.complete.educationalinfo.EducationalInformationFragment;
import ir.madreseplus.ui.view.login.complete.password.ConfirmPasswordFragment;
import ir.madreseplus.ui.view.login.complete.userinformation.UserInformationFragment;
import ir.madreseplus.utilities.Config;
import ir.madreseplus.utilities.DeviceInfo;
import ir.madreseplus.utilities.LoadingDialog;

import static ir.madreseplus.utilities.Config.USER_GENDER_KEY;
import static ir.madreseplus.utilities.Config.USER_MOBILE_KEY;

public class CompleteRegisterActivity extends BaseActivity<ActivityCompleteRegisterBinding> implements RegisterationCallback, CompleteRegisterNavigator {
    public static final String UserInfoTag = "UserInformation";
    public static final String ConfirmPassTag = "ConfirmPassword";
    public static final String EduInfoTag = " EducationalInformation";


    private CompleteRegisterViewModel viewModel;
    private User mUser;
    private LoadingDialog loadingDialog;

    @Override
    protected int layoutRes() {
        return R.layout.activity_complete_register;
    }

    @Override
    protected void setBinding() {
        binding = ActivityCompleteRegisterBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProviderFactory factory = Injection.providerFactory(this);
        viewModel = ViewModelProviders.of(this, factory).get(CompleteRegisterViewModel.class);
        viewModel.setNavigator(this);

        loadingDialog = new LoadingDialog(this);


        init();
    }

    private void init() {
        mUser = new User();
        addUserInfoFragment();
    }

    private void addUserInfoFragment() {
        UserInformationFragment userInformationFragment = new UserInformationFragment();
        userInformationFragment.setCallback(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, userInformationFragment, UserInformationFragment.class.getSimpleName())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    private void addEduInfoFragment() {
        EducationalInformationFragment userInformationFragment = new EducationalInformationFragment();
        userInformationFragment.setCallback(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                userInformationFragment, EducationalInformationFragment.class.getSimpleName()).addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .commit();
    }

    private void addConfirmPassFragment() {
        ConfirmPasswordFragment userInformationFragment = new ConfirmPasswordFragment();
        userInformationFragment.setCallback(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                userInformationFragment, ConfirmPasswordFragment.class.getSimpleName()).addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .commit();
    }

    @Override
    public void callBack(User user, String tag) {
        switch (tag) {
            case UserInfoTag:
                fillUserInformation(user);
                addEduInfoFragment();
                break;
            case EduInfoTag:
                fillUserEduInfo(user);
                addConfirmPassFragment();
                break;
            case ConfirmPassTag:
                loadingDialog.showDialog();
                fillUserPass(user);
                viewModel.completeRegister(mUser);
                break;

        }
    }

    private void fillUserPass(User user) {
        mUser.setPassword(user.getPassword());
        mUser.setToken(Hawk.get(Config.TOKEN_KEY));
        mUser.setPhone(Hawk.get(USER_MOBILE_KEY));
        mUser.setDevice_model(DeviceInfo.deviceName());
    }

    private void fillUserEduInfo(User user) {
        mUser.setGrade(user.getGrade());
        mUser.setField(user.getField());
        mUser.setExam(user.getExam());
    }

    private void fillUserInformation(User user) {
        mUser.setFirst_name(user.getFirst_name());
        mUser.setLast_name(user.getLast_name());
        mUser.setGender(user.getGender());
        mUser.setProvince_id(user.getProvince_id());
        mUser.setCity_id(user.getCity_id());
        mUser.setInvitation_code(user.getInvitation_code());
    }

    @Override
    public void error(Throwable throwable) {
        loadingDialog.hideDialog();
    }

    @Override
    public void complementResult(ComplementRes complementRes) {
        loadingDialog.hideDialog();
        if (complementRes.getStatus() == 200) {
            viewModel.getProfile();
        } else {
            Toast.makeText(CompleteRegisterActivity.this, complementRes.getError(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void profileResult(List<Student> students) {
        Hawk.put(USER_GENDER_KEY, students.get(0).getUserProfile().getGender());

        ir.madreseplus.data.model.entity.Student student = new ir.madreseplus.data.model.entity.Student();
        student.setGrade(students.get(0).getGetGradeDisplay());
        student.setFieldStudy(students.get(0).getGetFieldDisplay());
        student.setLastName(students.get(0).getUserProfile().getLastName());
        student.setFirstName(students.get(0).getUserProfile().getFirstName());
        student.setSchoolName(students.get(0).getSchoolName());
        student.setGenderTypeEnum(students.get(0).getUserProfile().getGender());

        viewModel.insertStudent(student);
        finish();
        startActivity(new Intent(CompleteRegisterActivity.this, MainActivity.class));
    }
}