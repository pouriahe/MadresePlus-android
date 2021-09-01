package ir.madreseplus.ui.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.orhanobut.hawk.Hawk;

import java.util.List;

import ir.madreseplus.Injection;
import ir.madreseplus.R;
import ir.madreseplus.ViewModelProviderFactory;
import ir.madreseplus.data.model.entity.Student;
import ir.madreseplus.data.model.enums.GenderTypeEnum;
import ir.madreseplus.databinding.ActivityMainBinding;
import ir.madreseplus.databinding.LayoutHeaderNavigartionBinding;
import ir.madreseplus.ui.base.BaseActivity;
import ir.madreseplus.ui.view.aboutus.AboutUsActivity;
import ir.madreseplus.ui.view.aricle.ArticleFragment;
import ir.madreseplus.ui.view.main.MainFragment;
import ir.madreseplus.ui.view.message.MessageFragment;
import ir.madreseplus.ui.view.schedule.ScheduleFragment;
import ir.madreseplus.ui.view.splash.SplashScreen;
import ir.madreseplus.ui.view.usage.UsageActivity;
import ir.madreseplus.utilities.Config;

import static ir.madreseplus.utilities.Config.TOKEN_KEY;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements MainActivityNavigator {

    private MainActivityViewModel mainActivityViewModel;

    private LayoutHeaderNavigartionBinding mHeaderBinding;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void setBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHeaderBinding = mHeaderBinding.bind(binding.getRoot());// nav headerLayout

        ViewModelProviderFactory factory = Injection.providerFactory(this);
        mainActivityViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);
        mainActivityViewModel.setNavigator(this);


        binding.bottomNavigation.setOnNavigationItemSelectedListener(this::bottomNavigationListener);
        binding.bottomNavigation.setSelectedItemId(R.id.item_home);

        drawerListeners();

        mainActivityViewModel.getProfile();
    }


    private void drawerListeners() {

        mHeaderBinding.lnUserGuideHeaderNav.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, UsageActivity.class));
        });


        mHeaderBinding.lnAboutMpHeaderNav.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
        });

        mHeaderBinding.lnCallToSupportHeaderNav.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(getString(R.string.tel_support))));
        });

        mHeaderBinding.lnExitAccountHeaderNav.setOnClickListener(v -> {
            Hawk.delete(TOKEN_KEY);
            finish();
            startActivity(new Intent(MainActivity.this, SplashScreen.class));
        });

    }

    public void setProfile(Student student) {
        mHeaderBinding.txtViewUserName.setText(student.getFirstName() + " " + student.getLastName());
        mHeaderBinding.txtViewDegree.setText(student.getGrade());
        mHeaderBinding.txtViewPhone.setText(Hawk.get(Config.USER_MOBILE_KEY));
    }

    private boolean bottomNavigationListener(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.item_home:
                clearScheduleDates();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_mainActivity, new MainFragment()).commit();
                return true;
            case R.id.mnu_item_messages:
                clearScheduleDates();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_mainActivity, new MessageFragment()).commit();
                return true;
            case R.id.item_article:
                clearScheduleDates();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_mainActivity, new ArticleFragment()).commit();
                return true;
//            case R.id.mnu_item_results:
//                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_mainActivity, new ResultFragment()).commit();
//                return true;
            case R.id.mnu_item_schedule:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_mainActivity, new ScheduleFragment()).commit();
                return true;
        }
        return false;
    }

    private void clearScheduleDates() {
        Hawk.delete(Config.DAY_KEY);
        Hawk.delete(Config.MONTH_KEY);
        Hawk.delete(Config.YEAR_KEY);
        Hawk.delete(Config.START_TIME_KEY);
        Hawk.delete(Config.END_TIME_KEY);
        Hawk.delete(Config.DAILY_KEY);
    }

    public void openDrawer() {
        binding.drawerLayout.openDrawer(Gravity.START);
    }

    public void closeDrawer() {
        binding.drawerLayout.closeDrawer(Gravity.END);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void error(Throwable throwable) {
        Toast.makeText(MainActivity.this, getString(R.string.rest_error), Toast.LENGTH_SHORT).show();
        Log.e("error", "error: " + throwable.getMessage());
    }

    @Override
    public void getProfile(List<Student> studentList) {
        if (studentList.size() > 0) {
            Student student = studentList.get(0);
            if (student.getGenderTypeEnum().equals(GenderTypeEnum.FEMALE)) {
                mHeaderBinding.rlHeaderNav.setBackground(getResources().getDrawable(R.drawable.selector_bg_haeder_avatart_girl));
                mHeaderBinding.rlNavContainer.setBackground(getResources().getDrawable(R.drawable.selector_header_nav_girl));
                mHeaderBinding.imgViewAvatar.setImageDrawable(getResources().getDrawable(R.drawable.ic_avatar_girl));
            }
        }
    }
}
