package ir.madreseplus.ui.view.schedule;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.orhanobut.hawk.Hawk;

import java.text.ParseException;
import java.util.Calendar;

import ir.madreseplus.R;
import ir.madreseplus.ui.base.ViewPagerAdapter;
import ir.madreseplus.ui.view.daily.DailyScheduleFragment;
import ir.madreseplus.ui.view.schedule.dailyreport.DailyReportDialog;
import ir.madreseplus.ui.view.weekly.WeeklyScheduleFragment;
import ir.madreseplus.utilities.Config;
import ir.madreseplus.utilities.DateParser;
import saman.zamani.persiandate.PersianDate;

public class ScheduleFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = ScheduleFragment.class.getSimpleName();

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView dateTextView;
    private FloatingActionButton mReportFloatingButton;
    private ViewPagerAdapter viewPagerAdapter;

    public Integer day, month, year;

    private Button chooseDateButton;


    public static ScheduleFragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
        setupViewPager();
        setListeners();

        day = Hawk.get(Config.DAY_KEY);
        month = Hawk.get(Config.MONTH_KEY);
        year = Hawk.get(Config.YEAR_KEY);

        if (day == null) {
            setDateTextView(null, null, null);
        } else {
            setDateTextView(year, month, day);
        }
    }

    private void setListeners() {
        chooseDateButton.setOnClickListener(v -> {
            PersianCalendar persianCalendar = new PersianCalendar();
            DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                    ScheduleFragment.this,
                    persianCalendar.getPersianYear(),
                    persianCalendar.getPersianMonth(),
                    persianCalendar.getPersianDay()
            );
            datePickerDialog.show(getActivity().getFragmentManager(), "Datepickerdialog");
        });

        mReportFloatingButton.setOnClickListener(v -> {
            DailyReportDialog dialog = DailyReportDialog.newInstance();
            dialog.show(getActivity().getSupportFragmentManager(), null);
        });
    }


    private void setupViewPager() {
        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPagerAdapter.addFragment(WeeklyScheduleFragment.newInstance(), getString(R.string.weekly_view_string_tab));
        viewPagerAdapter.addFragment(DailyScheduleFragment.newInstance(), getString(R.string.daily_view_string_tab));
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initViews(View view) {
        tabLayout = view.findViewById(R.id.tabLayout_scheduleFragment);
        viewPager = view.findViewById(R.id.viewPager_scheduleFragment);
        chooseDateButton = view.findViewById(R.id.btn_chooseDate_scheduleFragment);
        dateTextView = view.findViewById(R.id.txtView_date_scheduleFragment);
        mReportFloatingButton = view.findViewById(R.id.floating_report);
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Hawk.put(Config.DAY_KEY, dayOfMonth);
        Hawk.put(Config.MONTH_KEY, monthOfYear + 1);
        Hawk.put(Config.YEAR_KEY, year);

        setDateTextView(year, monthOfYear + 1, dayOfMonth);
    }

    private void setDateTextView(Integer year, Integer monthOfYear, Integer dayOfMonth) {
        PersianDate persianDate = new PersianDate();
        int[] grg = new int[]{};
        if (year != null) {
            grg = persianDate.toGregorian(year, monthOfYear, dayOfMonth);
            persianDate.setGrgYear(grg[0]);
            persianDate.setGrgMonth(grg[1]);
            persianDate.setGrgDay(grg[2]);


            getData(persianDate);
        }


        String date = persianDate.dayName() + " " + persianDate.getShDay() + " " +
                persianDate.monthName() + " " + persianDate.getShYear();
        dateTextView.setText(date);

    }

    private void getData(PersianDate pd) {
        try {
            Fragment fragment = viewPagerAdapter.getItem(viewPager.getCurrentItem());
            if (fragment.isVisible()) {
                String startPeriod = DateParser.parse(pd.toDate());
                String endPeriod = DateParser.parse(pd.toDate());


                if (fragment instanceof DailyScheduleFragment) {
                    Hawk.put(Config.DAILY_KEY, startPeriod);
                    ((DailyScheduleFragment) fragment).getEvents(startPeriod, endPeriod);
                } else if (fragment instanceof WeeklyScheduleFragment) {
                    String[] periods = getFirstAndLastWeekDate(pd.getGrgYear(), pd.getGrgMonth(), pd.getGrgDay());

                    Hawk.put(Config.START_TIME_KEY, periods[0]);
                    Hawk.put(Config.END_TIME_KEY, periods[1]);

                    ((WeeklyScheduleFragment) fragment).getEvents(periods[0], periods[1]);
                }
            }
        } catch (ParseException e) {
            Log.e(TAG, e.toString());
        }
    }

    private String[] getFirstAndLastWeekDate(int year, int month, int day) {
        try {
            // get today and clear time of day
            Calendar cal = Calendar.getInstance();
            cal.set(year, month - 1, day);
            cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
            cal.clear(Calendar.MINUTE);
            cal.clear(Calendar.SECOND);
            cal.clear(Calendar.MILLISECOND);
            cal.setFirstDayOfWeek(Calendar.SATURDAY);
            cal.getTime();

            cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
            String startDate = DateParser.parse(cal.getTime());

            // start of the next week
            cal.add(Calendar.DATE, 6);
            String endDate = DateParser.parse(cal.getTime());

            return new String[]{startDate, endDate};
        } catch (ParseException e) {
            Log.e(TAG, e.toString());
        }
        return null;
    }

}