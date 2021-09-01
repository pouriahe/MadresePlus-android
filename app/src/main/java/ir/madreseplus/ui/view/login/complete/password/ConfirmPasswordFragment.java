package ir.madreseplus.ui.view.login.complete.password;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ir.madreseplus.R;
import ir.madreseplus.data.model.req.User;
import ir.madreseplus.databinding.FragmentConfirmPasswordBinding;
import ir.madreseplus.ui.base.BaseFragment;
import ir.madreseplus.ui.view.login.complete.CompleteRegisterActivity;
import ir.madreseplus.ui.view.login.complete.RegisterationCallback;
import ir.madreseplus.utilities.SimpleTextWatcher;


public class ConfirmPasswordFragment extends BaseFragment<FragmentConfirmPasswordBinding> {

    private RegisterationCallback callback;
    private User user;


    @Override
    protected void setBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentConfirmPasswordBinding.inflate(inflater, container, false);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_confirm_password;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = new User();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        init();
        setListener();
    }

    private void init() {
        binding.etConfirmPass.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.inputLayerConfirmPass.setErrorEnabled(false);
            }
        });

        binding.etPass.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.inputLayerPass.setErrorEnabled(false);
            }
        });

    }

    private void setListener() {
        binding.btnNextStep.setOnClickListener(v -> {
            if (valid()) {
                user.setPassword(binding.etPass.getText().toString().trim());
                if (callback != null) {
                    callback.callBack(user, CompleteRegisterActivity.ConfirmPassTag);
                }
            }
        });
        binding.rlPreviousStep.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });
    }

    private boolean valid() {
        String pass = binding.etPass.getText().toString().trim();
        String confirmPass = binding.etConfirmPass.getText().toString().trim();
        if (pass.length() < 8) {
            binding.inputLayerPass.setError("حداقل باید 8 کاراکتر باشد!");
            return false;
        }
        if (!confirmPass.equalsIgnoreCase(pass)) {
            binding.inputLayerConfirmPass.setError("رمز عبور و تکرار آن با هم یکسان نیست!");
//            Toast.makeText(getContext(), "رمز عبور و تکرار آن با هم یکسان نیست!", Toast.LENGTH_LONG).show();
            binding.inputLayerConfirmPass.setErrorEnabled(true);
            return false;
        }
        return true;
    }

    public void setCallback(RegisterationCallback registerationCallback) {
        this.callback = registerationCallback;
    }
}

