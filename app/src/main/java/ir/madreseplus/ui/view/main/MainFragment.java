package ir.madreseplus.ui.view.main;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.hawk.Hawk;

import java.util.List;

import ir.madreseplus.Injection;
import ir.madreseplus.R;
import ir.madreseplus.ViewModelProviderFactory;
import ir.madreseplus.data.model.entity.Student;
import ir.madreseplus.data.model.enums.EventTypeEnum;
import ir.madreseplus.data.model.enums.GenderTypeEnum;
import ir.madreseplus.data.model.req.Dashboard;
import ir.madreseplus.data.model.req.Event;
import ir.madreseplus.ui.base.OnRvItemClickListener;
import ir.madreseplus.ui.view.practice.PracticeActivity;
import ir.madreseplus.ui.view.MainActivity;
import ir.madreseplus.ui.view.task.TaskActivity;
import ir.madreseplus.utilities.Config;
import ir.madreseplus.utilities.LoadingDialog;
import saman.zamani.persiandate.PersianDate;


public class MainFragment extends Fragment implements MainFNavigator<Dashboard>, OnRvItemClickListener<Event> {
    private static final String TAG = MainFragment.class.getSimpleName();


    private TextView progressTextView, examTextView, todayTextView, monthNameTextView, testTextView, nameTextView, degreeTextView,
            noPlan;
    private ProgressBar progressBar;
    private RecyclerView rvMainFragment;
    private EventsAdapter eventsAdapter;
    private LinearLayout toolbarLinearLayout;
    private ImageView genderImageView;

    private MainFViewModel mainFViewModel;
    private LoadingDialog loadingDialog;
    private ObjectAnimator progressAnimator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageView toggle = view.findViewById(R.id.imgView_toggle_mainFragment);
        toggle.setOnClickListener(v -> ((MainActivity) getActivity()).openDrawer());

        ViewModelProviderFactory factory = Injection.providerFactory(getContext());
        mainFViewModel = ViewModelProviders.of(this, factory).get(MainFViewModel.class);
        mainFViewModel.setNavigator(this);

        initView(view);

        loadingDialog = new LoadingDialog(getContext());

    }

    private void initView(View view) {
        nameTextView = view.findViewById(R.id.txtView_name_mainFragment);
        testTextView = view.findViewById(R.id.txtView_testDay_mainFragment);
        examTextView = view.findViewById(R.id.txtView_finalexamDay_mainFragment);
        degreeTextView = view.findViewById(R.id.txtView_degree_mainFragment);
        progressTextView = view.findViewById(R.id.txtView_progressTask_mainFragment);
        todayTextView = view.findViewById(R.id.txtView_day_mainFragment);
        monthNameTextView = view.findViewById(R.id.txtView_monthName_mainFragment);
        progressBar = view.findViewById(R.id.pg_percent_mainFragment);
        rvMainFragment = view.findViewById(R.id.rv_tasks_mainFragment);
        toolbarLinearLayout = view.findViewById(R.id.ln_containerHeader_mainFragment);
        genderImageView = view.findViewById(R.id.imgView_gender);
        noPlan = view.findViewById(R.id.txt_noPlan);

        GenderTypeEnum gender = Hawk.get(Config.USER_GENDER_KEY);
        if (gender.equals(GenderTypeEnum.FEMALE)) {
            toolbarLinearLayout.setBackground(getContext().getResources().getDrawable(R.drawable.selector_tool_main_girl));
            genderImageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable.bg_girl));
            progressBar.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.custom_progress_bar_horizontal_girl));
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void error(Throwable throwable) {
        loadingDialog.hideDialog();
        Log.e(TAG, "error: " + throwable.getMessage());
    }

    @Override
    public void home(Dashboard dashboard) {
        loadingDialog.hideDialog();

        PersianDate pdate = new PersianDate();
        if (dashboard.getTaskProgress() != 0) {
            progressTextView.setText("آفرین! " + dashboard.getTaskProgress() + " درصد برنامه امروزتو انجام دادی");
        }
        todayTextView.setText(String.valueOf(pdate.getShDay()));
        testTextView.setText(String.valueOf(dashboard.getExamTime()));
        examTextView.setText(String.valueOf(dashboard.getFinalExamTime()));
        monthNameTextView.setText(pdate.monthName());

        progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, dashboard.getTaskProgress());
        progressAnimator.setDuration(2000);
        progressAnimator.start();


        if (dashboard.getEvents().size() > 0) {
            noPlan.setVisibility(View.GONE);
            rvMainFragment.setVisibility(View.VISIBLE);
            setupRv(dashboard.getEvents());
        } else {
            noPlan.setVisibility(View.VISIBLE);
            rvMainFragment.setVisibility(View.GONE);
        }
    }


    //setup recycler view
    private void setupRv(List<Event> events) {
        eventsAdapter = new EventsAdapter(events);
        rvMainFragment.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rvMainFragment.setAdapter(eventsAdapter);


        //onClick listener
        eventsAdapter.setOnRvItemClickListener(this);
    }

    @Override
    public void profile(List<Student> studentList) {
        ((MainActivity) getActivity()).setProfile(studentList.get(0));
        nameTextView.setText("سلام " + studentList.get(0).getFirstName());
        degreeTextView.setText(studentList.get(0).getGrade());
    }

    @Override
    public void onItemClick(Event item, int position) {
        if (item.getKind().equals(EventTypeEnum.PRACTICE)) {
            Intent intent = new Intent(getActivity(), PracticeActivity.class);
            intent.putExtra(Config.ID_KEY, item.getPk());
            getContext().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            Intent intent = new Intent(getActivity(), TaskActivity.class);
            intent.putExtra(Config.ID_KEY, item.getPk());
            getContext().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        loadingDialog.showDialog();
        mainFViewModel.profile();
        mainFViewModel.home();
    }
}
