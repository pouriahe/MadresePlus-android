package ir.madreseplus.ui.view.login.complete.educationalinfo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import ir.madreseplus.R;
import ir.madreseplus.data.model.req.User;
import ir.madreseplus.databinding.FragmentEducationalInformationBinding;
import ir.madreseplus.ui.base.BaseFragment;
import ir.madreseplus.ui.view.login.complete.CompleteRegisterActivity;
import ir.madreseplus.ui.view.login.complete.RegisterationCallback;

public class EducationalInformationFragment extends BaseFragment<FragmentEducationalInformationBinding> {

    private RegisterationCallback callback;
    private User user;
    private GradeAdapter gradeAdapter;
    private MajorAdapter majorAdapter;
    private RegistrationTestAdapter registrationAdapter;

    @Override
    protected void setBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentEducationalInformationBinding.inflate(inflater, container, false);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_educational_information;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = new User();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setListener();
        fillMajorAutoComplete();
        fillGradeAutoComplete();
        fillRegistrationTestAutoComplete();
    }

    private void fillGradeAutoComplete() {
        ArrayList<Grade> gradeList = new ArrayList();
        gradeList.add(new Grade(10, "دهم"));
        gradeList.add(new Grade(11, "یازدهم"));
        gradeList.add(new Grade(12, "دوازدهم"));
        gradeList.add(new Grade(13, "فارغ التحصیل"));
        gradeAdapter = new GradeAdapter(getContext(), android.R.layout.simple_list_item_1, gradeList);
        gradeAdapter.setGradeCallback(grade -> {
            user.setGrade(grade.getId());
            binding.autoCompleteGrade.setText(grade.getName());
        });
        binding.autoCompleteGrade.setAdapter(gradeAdapter);


    }

    private void fillRegistrationTestAutoComplete() {
        ArrayList<RegistrationTest> registrationList = new ArrayList();
        registrationList.add(new RegistrationTest(1, "قلمچی"));
        registrationList.add(new RegistrationTest(2, "گزینه دو"));
        registrationList.add(new RegistrationTest(3, "سنجش"));
        registrationList.add(new RegistrationTest(4, "گاج"));
        registrationList.add(new RegistrationTest(5, "سایر"));
        registrationList.add(new RegistrationTest(0, "آزمونی شرکت نمیکنم"));
        registrationAdapter = new RegistrationTestAdapter(getContext(), android.R.layout.simple_list_item_1, registrationList);
        registrationAdapter.setRegistrationTestCallback(item -> {
            user.setExam(item.getId());
            binding.autoCompleteRegistrationTest.setText(item.getName());
        });
        binding.autoCompleteRegistrationTest.setAdapter(registrationAdapter);
    }

    private void fillMajorAutoComplete() {
        ArrayList<Major> majorList = new ArrayList();
        majorList.add(new Major(1, "ریاضی"));
        majorList.add(new Major(2, "تجربی"));
        majorList.add(new Major(3, "انسانی"));
        majorAdapter = new MajorAdapter(getContext(), android.R.layout.simple_list_item_1, majorList);
        majorAdapter.setMajorCallback(major -> {
            user.setField(major.getId());
            binding.autoCompleteMajor.setText(major.getName());
        });
        binding.autoCompleteMajor.setAdapter(majorAdapter);
    }

    private void setListener() {
        binding.rlPreviousStep.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        });

        binding.btnNextStep.setOnClickListener(v -> {
            if (valid()) {
                if (callback != null) callback.callBack(user, CompleteRegisterActivity.EduInfoTag);
            }
        });
    }

    private boolean valid() {
        if (user.getField() == null) {
            binding.inputLayerMajor.setError("لطفا یک مقدار انتخاب کنید");
            return false;
        } else if (user.getGrade() == null) {
            binding.inputLayerGrade.setError("لطفا یک مقدار انتخاب کنید");
            return false;
        }else if (user.getExam() == null) {
            binding.inputLayerRegistrationTest.setError("لطفا یک مقدار انتخاب کنید");
            return false;
        }
        return true;
    }

    public void setCallback(RegisterationCallback registerationCallback) {
        this.callback = registerationCallback;
    }

}