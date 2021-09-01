package ir.madreseplus.ui.view.login.complete.userinformation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import ir.madreseplus.Injection;
import ir.madreseplus.R;
import ir.madreseplus.ViewModelProviderFactory;
import ir.madreseplus.data.model.req.User;
import ir.madreseplus.data.model.res.CityRes;
import ir.madreseplus.data.model.res.ProvinceRes;
import ir.madreseplus.databinding.FragmentUserInformationBinding;
import ir.madreseplus.ui.base.BaseFragment;
import ir.madreseplus.ui.view.login.complete.CompleteRegisterActivity;
import ir.madreseplus.ui.view.login.complete.RegisterationCallback;
import ir.madreseplus.ui.view.login.complete.educationalinfo.Grade;
import ir.madreseplus.ui.view.login.complete.educationalinfo.GradeAdapter;
import ir.madreseplus.ui.view.login.complete.userinformation.city.CityCallback;
import ir.madreseplus.ui.view.login.complete.userinformation.city.CityDialog;
import ir.madreseplus.ui.view.login.complete.userinformation.province.ProvinceCallback;
import ir.madreseplus.ui.view.login.complete.userinformation.province.ProvinceDialog;
import ir.madreseplus.utilities.SimpleTextWatcher;
import ir.madreseplus.utilities.StringUtil;

public class UserInformationFragment extends BaseFragment<FragmentUserInformationBinding> implements UserInformationNavigator, ProvinceCallback, CityCallback {
    private static final String TAG = "UserInformationFragment";

    private UserInformationViewModel viewModel;
    private RegisterationCallback callback;
    private GenderAdapter genderAdapter;
    private User user;
    private List<ProvinceRes> provinceResList;
    private List<CityRes> cityList;


    @Override
    protected void setBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentUserInformationBinding.inflate(inflater, container, false);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_user_information;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProviderFactory factory = Injection.providerFactory(getContext());
        viewModel = ViewModelProviders.of(this, factory).get(UserInformationViewModel.class);
        viewModel.setNavigator(UserInformationFragment.this);

        user = new User();

        viewModel.getProvinces();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        init();
        fillGenderAutoComplete();
        setListener();
    }

    private void init() {
        binding.etFirstName.requestFocus();
        binding.etFirstName.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.inputLayerFirstName.setErrorEnabled(false);
                user.setFirst_name(s.toString());
            }
        });
        binding.etLastName.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.inputLayerLastName.setErrorEnabled(false);
                user.setLast_name(s.toString());
            }
        });
        binding.autoCompleteGender.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.inputLayerGender.setErrorEnabled(false);
            }
        });
        binding.etProvince.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.inputLayerProvince.setErrorEnabled(false);
            }
        });
        binding.etCity.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.inputLayerCity.setErrorEnabled(false);
            }
        });
        binding.etInvitationCode.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.inputLayerInvitationCode.setErrorEnabled(false);
                user.setInvitation_code(s.toString());
            }
        });
    }

    private void setListener() {

        binding.etProvince.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                if (provinceResList != null) {
                    ProvinceDialog dialog = ProvinceDialog.newInstance(provinceResList);
                    dialog.show(getActivity().getSupportFragmentManager(), null);
                    dialog.setProvinceCallback(this);
                } else {
                    Toast.makeText(getContext(), "خطا در بارگزاری استانها", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.etCity.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                if (cityList != null) {
                    CityDialog dialog = CityDialog.newInstance(cityList);
                    dialog.show(getActivity().getSupportFragmentManager(), null);
                    dialog.setCityCallback(this);
                } else {
                    Toast.makeText(getContext(), "خطا در بارگزاری استانها", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnNextStep.setOnClickListener(v -> {
            if (valid()) {
                if (callback != null) {
                    callback.callBack(user, CompleteRegisterActivity.UserInfoTag);
                }
            }
        });

    }

    private boolean valid() {
        if (!StringUtil.hasText(user.getFirst_name())) {
            binding.inputLayerFirstName.setError("لطفا نام را وارد کنید!");
            return false;
        }
        if (!StringUtil.hasText(user.getLast_name())) {
            binding.inputLayerLastName.setError("لطفا نام خانوادگی را وارد کنید!");
            return false;
        }
        if (user.getGender() == null) {
            binding.inputLayerGender.setError("لطفا جنسیت را وارد کنید!");
            return false;
        }
        if (user.getProvince_id() == null) {
            binding.inputLayerProvince.setError("لطفا استان  را انتخاب کنید!");
            return false;
        }
        if (user.getCity_id() == null) {
            binding.inputLayerCity.setError("لطفا شهر  را انتخاب کنید!");
            return false;
        }
        return true;
    }

    private void fillGenderAutoComplete() {

        ArrayList<Gender> genderList = new ArrayList();
        genderList.add(new Gender(0, "مرد"));
        genderList.add(new Gender(1, "زن"));

        genderAdapter = new GenderAdapter(getContext(), android.R.layout.simple_list_item_1, genderList);
        genderAdapter.setGenderCallback(grade -> {
            user.setGender(grade.getId());
            binding.autoCompleteGender.setText(grade.getName());
        });
        binding.autoCompleteGender.setAdapter(genderAdapter);
    }

    public void setCallback(RegisterationCallback registerationCallback) {
        this.callback = registerationCallback;
    }

    @Override
    public void error(Throwable throwable) {
        Log.e(TAG, "error:" + throwable.getMessage());
    }

    @Override
    public void provinceRes(List<ProvinceRes> provinces) {
        provinceResList = provinces;
    }


    @Override
    public void cityRes(List<CityRes> cities) {
        this.cityList = cities;
    }

    @Override
    public void onProvinceSelect(ProvinceRes province) {
        binding.etProvince.setText(province.getName());
        user.setProvince_id(province.getId());
        viewModel.getCities(province.getId());
    }

    @Override
    public void onCitySelect(CityRes city) {
        binding.etCity.setText(city.getName());
        user.setCity_id(city.getId());
    }
}