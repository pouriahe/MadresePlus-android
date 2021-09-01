package ir.madreseplus.ui.view.daily;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.hawk.Hawk;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import ir.madreseplus.Injection;
import ir.madreseplus.R;
import ir.madreseplus.ViewModelProviderFactory;
import ir.madreseplus.data.model.enums.EventTypeEnum;
import ir.madreseplus.data.model.req.Event;
import ir.madreseplus.data.model.req.Task;
import ir.madreseplus.ui.base.OnRvItemClickListener;
import ir.madreseplus.ui.view.practice.PracticeActivity;
import ir.madreseplus.ui.view.task.TaskActivity;
import ir.madreseplus.utilities.Config;
import ir.madreseplus.utilities.DateParser;
import ir.madreseplus.utilities.LoadingDialog;
import ir.madreseplus.utilities.StringUtil;

public class DailyScheduleFragment extends Fragment implements DailyScheduleNavigator<Task>, OnRvItemClickListener<Event> {
    private static final String TAG = DailyScheduleFragment.class.getSimpleName();


    private RecyclerView dailySchRecyclerView;
    private LoadingDialog loadingDialog;
    private DailyScheduleViewModel dailyScheduleViewModel;
    private DailyScheduleAdapter dailyScheduleAdapter;
    private TextView noPlan;
    private String dailySchedule;

    public static DailyScheduleFragment newInstance() {
        Bundle args = new Bundle();
        DailyScheduleFragment fragment = new DailyScheduleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_layout, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ViewModelProviderFactory factory = Injection.providerFactory(getContext());
        dailyScheduleViewModel = ViewModelProviders.of(this, factory).get(DailyScheduleViewModel.class);
        dailyScheduleViewModel.setNavigator(this);
        loadingDialog = new LoadingDialog(getContext());
        initViews(view);

        try {
            Date date = new Date();
            dailyScheduleViewModel.events(DateParser.parse(date), DateParser.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

       /* dailySchedule = Hawk.get(Config.DAILY_KEY);*/
    }

    public void getEvents(String start, String end) {
        loadingDialog.showDialog();
        dailyScheduleViewModel.events(start, end);
    }

    private void initViews(View view) {
        dailySchRecyclerView = view.findViewById(R.id.rv_dailySchedule);
        noPlan = view.findViewById(R.id.txt_noPlan);
    }

    @Override
    public void error(Throwable throwable) {
        loadingDialog.hideDialog();
        Toast.makeText(getContext(), getString(R.string.rest_error), Toast.LENGTH_SHORT).show();
        Log.e(TAG, "error: " + throwable.getMessage());
    }

    @Override
    public void events(List<Event> events) {
        loadingDialog.hideDialog();

        if (events.size() > 0) {
            noPlan.setVisibility(View.GONE);
            dailySchRecyclerView.setVisibility(View.VISIBLE);

            dailyScheduleAdapter = new DailyScheduleAdapter(events);
            dailySchRecyclerView.setAdapter(dailyScheduleAdapter);
            dailyScheduleAdapter.setOnRvItemClickListener(this);

        } else {
            noPlan.setVisibility(View.VISIBLE);
            dailySchRecyclerView.setVisibility(View.GONE);
        }
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

        dailySchedule = Hawk.get(Config.DAILY_KEY);
        if (StringUtil.hasText(dailySchedule)) {
            getEvents(dailySchedule, dailySchedule);
        }
    }
}
