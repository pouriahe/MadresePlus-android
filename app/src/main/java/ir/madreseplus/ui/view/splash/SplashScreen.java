package ir.madreseplus.ui.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import ir.madreseplus.Injection;
import ir.madreseplus.R;
import ir.madreseplus.ViewModelProviderFactory;
import ir.madreseplus.data.model.res.Splash;
import ir.madreseplus.databinding.ActivitySplashScreenBinding;
import ir.madreseplus.ui.base.BaseActivity;
import ir.madreseplus.ui.view.MainActivity;
import ir.madreseplus.ui.view.login.LoginActivity;
import ir.madreseplus.ui.view.login.complete.CompleteRegisterActivity;
import retrofit2.HttpException;

import static ir.madreseplus.utilities.Config.COMPLEMENT_REGISTER;
import static ir.madreseplus.utilities.Config.UNAUTHORIZED;

public class SplashScreen extends BaseActivity<ActivitySplashScreenBinding> implements SplashNavigator {
    private static final String TAG = SplashScreen.class.getSimpleName();

    private SplashViewModel splashViewModel;

    @Override
    protected int layoutRes() {
        return R.layout.activity_splash_screen;
    }

    protected void setBinding() {
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        ViewModelProviderFactory factory = Injection.providerFactory(this);
        splashViewModel = ViewModelProviders.of(this, factory).get(SplashViewModel.class);
        splashViewModel.setNavigator(this);

        setListeners();

        splashViewModel.authChecker();
    }

    private void setListeners() {
        binding.txtViewRetryNetwork.setOnClickListener(v -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            splashViewModel.authChecker();
        });
    }

    private void showLogin() {
        new Handler().postDelayed(() -> {
            finish();
            startActivity(new Intent(SplashScreen.this, LoginActivity.class));
        }, 1500);
    }

    private void showMain() {
        new Handler().postDelayed(() -> {
            finish();
            startActivity(new Intent(SplashScreen.this, MainActivity.class));
        }, 1500);
    }

    @Override
    public void error(Throwable throwable) {
        binding.progressBar.setVisibility(View.INVISIBLE);
        if (((HttpException) throwable).code() == UNAUTHORIZED) {
            showLogin();
        } else {
            Toast.makeText(SplashScreen.this, R.string.rest_error, Toast.LENGTH_SHORT).show();
            binding.txtViewRetryNetwork.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void authCheck(Splash splash) {
        binding.progressBar.setVisibility(View.INVISIBLE);
        if (splash.getNextState() != null && splash.getNextState() == COMPLEMENT_REGISTER) {
            finish();
            startActivity(new Intent(SplashScreen.this, CompleteRegisterActivity.class));
        } else {
            showMain();
        }

    }
}
