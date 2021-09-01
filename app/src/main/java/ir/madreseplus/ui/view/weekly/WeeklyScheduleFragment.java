package ir.madreseplus.ui.view.weekly;

import android.content.Intent;
import android.graphics.Typeface;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ir.madreseplus.Injection;
import ir.madreseplus.R;
import ir.madreseplus.ViewModelProviderFactory;
import ir.madreseplus.data.model.enums.EventTypeEnum;
import ir.madreseplus.data.model.req.Event;
import ir.madreseplus.ui.base.OnRvItemClickListener;
import ir.madreseplus.ui.view.daily.DailyScheduleFragment;
import ir.madreseplus.ui.view.practice.PracticeActivity;
import ir.madreseplus.ui.view.task.TaskActivity;
import ir.madreseplus.utilities.Config;
import ir.madreseplus.utilities.DateParser;
import ir.madreseplus.utilities.LoadingDialog;
import ir.madreseplus.utilities.StringUtil;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class WeeklyScheduleFragment extends Fragment implements WeeklyScheduleNavigator<Event>, OnRvItemClickListener<Event> {
    private static final String TAG = WeeklyScheduleFragment.class.getSimpleName();

    private LoadingDialog loadingDialog;
    private WeeklyScheduleViewModel viewModel;
    private RecyclerView satRv, sunRv, monRv, tueRv, wedRv, thuRv, friRv;
    private TextView satDate, sunDate, monDate, tueDate, wedDate, thuDate, friDate,
            satStr, sunStr, monStr, tueStr, wedStr, thuStr, friStr,
            satNoPlan, sunNoPlan, monNoPlan, tueNoPlan, wedNoPlan, thuNoPlan, friNoPlan;

    private String start, end;

    public static WeeklyScheduleFragment newInstance() {
        Bundle args = new Bundle();

        WeeklyScheduleFragment fragment = new WeeklyScheduleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weekly_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ViewModelProviderFactory factory = Injection.providerFactory(getContext());
        viewModel = ViewModelProviders.of(this, factory).get(WeeklyScheduleViewModel.class);
        viewModel.setNavigator(this);

        initViews(view);

    }

    private void initViews(View view) {
        satRv = view.findViewById(R.id.rv_saturday);
        sunRv = view.findViewById(R.id.rv_sunday);
        monRv = view.findViewById(R.id.rv_monday);
        tueRv = view.findViewById(R.id.rv_tuesday);
        wedRv = view.findViewById(R.id.rv_wednesday);
        thuRv = view.findViewById(R.id.rv_thursday);
        friRv = view.findViewById(R.id.rv_friday);
        satDate = view.findViewById(R.id.txtView_satDate);
        sunDate = view.findViewById(R.id.txtView_sunDate);
        monDate = view.findViewById(R.id.txtView_monDate);
        tueDate = view.findViewById(R.id.txtView_tueDate);
        wedDate = view.findViewById(R.id.txtView_wedDate);
        thuDate = view.findViewById(R.id.txtView_thuDate);
        friDate = view.findViewById(R.id.txtView_friDate);
        satNoPlan = view.findViewById(R.id.txtSat_noPlan);
        sunNoPlan = view.findViewById(R.id.txtSun_noPlan);
        monNoPlan = view.findViewById(R.id.txtMon_noPlan);
        tueNoPlan = view.findViewById(R.id.txtTue_noPlan);
        wedNoPlan = view.findViewById(R.id.txtWed_noPlan);
        thuNoPlan = view.findViewById(R.id.txtThu_noPlan);
        friNoPlan = view.findViewById(R.id.txtFri_noPlan);
        satStr = view.findViewById(R.id.txtView_strSat);
        sunStr = view.findViewById(R.id.txtView_strSun);
        monStr = view.findViewById(R.id.txtView_strMon);
        tueStr = view.findViewById(R.id.txtView_strTue);
        wedStr = view.findViewById(R.id.txtView_strWed);
        thuStr = view.findViewById(R.id.txtView_strThu);
        friStr = view.findViewById(R.id.txtView_strFri);
    }

    public void getEvents(String startPeriod, String endPeriod) {
        loadingDialog.showDialog();
        setDateTextViews(startPeriod);
        viewModel.events(startPeriod, endPeriod);
    }

    private void setDateTextViews(String startPeriod) {
        try {
            Date date = DateParser.parse(startPeriod);
            PersianDate pDate = new PersianDate(date);
            PersianDateFormat pDateFormatter = new PersianDateFormat("Y/m/d");

            Calendar cal = Calendar.getInstance();
            cal.set(pDate.getGrgYear(), pDate.getGrgMonth() - 1, pDate.getGrgDay());
            cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
            cal.clear(Calendar.MINUTE);
            cal.clear(Calendar.SECOND);
            cal.clear(Calendar.MILLISECOND);
            cal.setFirstDayOfWeek(Calendar.SATURDAY);
            cal.getTime();

            for (int i = 0; i < 7; i++) {
                switch (i) {
                    case 0:
                        pDate = new PersianDate(cal.getTime());
                        satDate.setText(pDateFormatter.format(pDate));
                        if (isToday(pDate) == 0) {
                            satDate.setTypeface(satDate.getTypeface(), Typeface.BOLD);
                            satStr.setTypeface(satStr.getTypeface(), Typeface.BOLD);
                        } else {
                            satDate.setTypeface(satDate.getTypeface(), Typeface.NORMAL);
                            satStr.setTypeface(satStr.getTypeface(), Typeface.NORMAL);
                        }
                        break;
                    case 1:
                        cal.add(Calendar.DATE, 1);
                        pDate = new PersianDate(cal.getTime());
                        sunDate.setText(pDateFormatter.format(pDate));
                        if (isToday(pDate) == 0) {
                            sunStr.setTypeface(sunStr.getTypeface(), Typeface.BOLD);
                            sunDate.setTypeface(sunDate.getTypeface(), Typeface.BOLD);
                        } else {
                            sunStr.setTypeface(satDate.getTypeface(), Typeface.NORMAL);
                            sunDate.setTypeface(satStr.getTypeface(), Typeface.NORMAL);
                        }
                        break;
                    case 2:
                        cal.add(Calendar.DATE, 1);
                        pDate = new PersianDate(cal.getTime());
                        monDate.setText(pDateFormatter.format(pDate));
                        if (isToday(pDate) == 0) {
                            monStr.setTypeface(monStr.getTypeface(), Typeface.BOLD);
                            monDate.setTypeface(monDate.getTypeface(), Typeface.BOLD);
                        } else {
                            monStr.setTypeface(satDate.getTypeface(), Typeface.NORMAL);
                            monDate.setTypeface(satStr.getTypeface(), Typeface.NORMAL);
                        }
                        break;
                    case 3:
                        cal.add(Calendar.DATE, 1);
                        pDate = new PersianDate(cal.getTime());
                        tueDate.setText(pDateFormatter.format(pDate));
                        if (isToday(pDate) == 0) {
                            tueStr.setTypeface(tueStr.getTypeface(), Typeface.BOLD);
                            tueDate.setTypeface(tueDate.getTypeface(), Typeface.BOLD);
                        } else {
                            tueStr.setTypeface(satDate.getTypeface(), Typeface.NORMAL);
                            tueDate.setTypeface(satStr.getTypeface(), Typeface.NORMAL);
                        }
                        break;
                    case 4:
                        cal.add(Calendar.DATE, 1);
                        pDate = new PersianDate(cal.getTime());
                        wedDate.setText(pDateFormatter.format(pDate));
                        if (isToday(pDate) == 0) {
                            wedStr.setTypeface(wedStr.getTypeface(), Typeface.BOLD);
                            wedDate.setTypeface(wedDate.getTypeface(), Typeface.BOLD);
                        } else {
                            wedStr.setTypeface(satDate.getTypeface(), Typeface.NORMAL);
                            wedDate.setTypeface(satStr.getTypeface(), Typeface.NORMAL);
                        }
                        break;
                    case 5:
                        cal.add(Calendar.DATE, 1);
                        pDate = new PersianDate(cal.getTime());
                        thuDate.setText(pDateFormatter.format(pDate));
                        if (isToday(pDate) == 0) {
                            thuStr.setTypeface(thuStr.getTypeface(), Typeface.BOLD);
                            thuDate.setTypeface(thuDate.getTypeface(), Typeface.BOLD);
                        } else {
                            thuStr.setTypeface(satDate.getTypeface(), Typeface.NORMAL);
                            thuDate.setTypeface(satStr.getTypeface(), Typeface.NORMAL);
                        }
                        break;
                    case 6:
                        cal.add(Calendar.DATE, 1);
                        pDate = new PersianDate(cal.getTime());
                        friDate.setText(pDateFormatter.format(pDate));
                        if (isToday(pDate) == 0) {
                            friStr.setTypeface(friStr.getTypeface(), Typeface.BOLD);
                            friDate.setTypeface(friDate.getTypeface(), Typeface.BOLD);
                        } else {
                            friStr.setTypeface(satDate.getTypeface(), Typeface.NORMAL);
                            friDate.setTypeface(satStr.getTypeface(), Typeface.NORMAL);
                        }
                        break;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "setDateTextViews: " + e.toString());
        }

    }

    private int isToday(PersianDate pDate) {
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = DateParser.parse(DateParser.parse(pDate.toDate()));
            d2 = DateParser.parse(DateParser.parse(new Date()));
        } catch (ParseException e) {

        }
        return d1.compareTo(d2);
    }


    @Override
    public void error(Throwable throwable) {
        loadingDialog.hideDialog();
        Toast.makeText(getContext(), getString(R.string.rest_error), Toast.LENGTH_SHORT).show();
        Log.e(TAG, "error: " + throwable.getMessage());
    }

    @Override
    public void events(List<Event> list) {
        loadingDialog.hideDialog();

        List<Event> satList = new ArrayList<>();
        List<Event> sunList = new ArrayList<>();
        List<Event> monList = new ArrayList<>();
        List<Event> tueList = new ArrayList<>();
        List<Event> wedList = new ArrayList<>();
        List<Event> thuList = new ArrayList<>();
        List<Event> friList = new ArrayList<>();

        try {
            for (Event event : list) {
                Date date = DateParser.parse(event.getDate());
                int dayOfWeek = getDayOfWeek(date);

                switch (dayOfWeek) {
                    case 0:
                        satList.add(event);
                        break;
                    case 1:
                        sunList.add(event);
                        break;
                    case 2:
                        monList.add(event);
                        break;
                    case 3:
                        tueList.add(event);
                        break;
                    case 4:
                        wedList.add(event);
                        break;
                    case 5:
                        thuList.add(event);
                        break;
                    case 6:
                        friList.add(event);
                        break;
                }
            }

            if (satList.size() > 0) {
                satRv.setVisibility(View.VISIBLE);
                satNoPlan.setVisibility(View.GONE);
                WeeklyScheduleAdapter satAdapter = new WeeklyScheduleAdapter(satList);
                satAdapter.setOnRvItemClickListener(this);
                satRv.setAdapter(satAdapter);
            } else {
                satRv.setVisibility(View.GONE);
                satNoPlan.setVisibility(View.VISIBLE);
            }

            if (sunList.size() > 0) {
                sunRv.setVisibility(View.VISIBLE);
                sunNoPlan.setVisibility(View.GONE);
                WeeklyScheduleAdapter sunAdapter = new WeeklyScheduleAdapter(sunList);
                sunAdapter.setOnRvItemClickListener(this);
                sunRv.setAdapter(sunAdapter);
            } else {
                sunRv.setVisibility(View.GONE);
                sunNoPlan.setVisibility(View.VISIBLE);
            }

            if (monList.size() > 0) {
                monRv.setVisibility(View.VISIBLE);
                monNoPlan.setVisibility(View.GONE);
                WeeklyScheduleAdapter monAdapter = new WeeklyScheduleAdapter(monList);
                monAdapter.setOnRvItemClickListener(this);
                monRv.setAdapter(monAdapter);
            } else {
                monRv.setVisibility(View.GONE);
                monNoPlan.setVisibility(View.VISIBLE);
            }

            if (tueList.size() > 0) {
                tueRv.setVisibility(View.VISIBLE);
                tueNoPlan.setVisibility(View.GONE);
                WeeklyScheduleAdapter tueAdapter = new WeeklyScheduleAdapter(tueList);
                tueAdapter.setOnRvItemClickListener(this);
                tueRv.setAdapter(tueAdapter);
            } else {
                tueRv.setVisibility(View.GONE);
                tueNoPlan.setVisibility(View.VISIBLE);
            }

            if (wedList.size() > 0) {
                wedRv.setVisibility(View.VISIBLE);
                wedNoPlan.setVisibility(View.GONE);
                WeeklyScheduleAdapter wedAdapter = new WeeklyScheduleAdapter(wedList);
                wedAdapter.setOnRvItemClickListener(this);
                wedRv.setAdapter(wedAdapter);
            } else {
                wedRv.setVisibility(View.GONE);
                wedNoPlan.setVisibility(View.VISIBLE);
            }

            if (thuList.size() > 0) {
                thuRv.setVisibility(View.VISIBLE);
                thuNoPlan.setVisibility(View.GONE);
                WeeklyScheduleAdapter thuAdapter = new WeeklyScheduleAdapter(thuList);
                thuAdapter.setOnRvItemClickListener(this);
                thuRv.setAdapter(thuAdapter);
            } else {
                thuRv.setVisibility(View.GONE);
                thuNoPlan.setVisibility(View.VISIBLE);
            }

            if (friList.size() > 0) {
                friRv.setVisibility(View.VISIBLE);
                friNoPlan.setVisibility(View.GONE);
                WeeklyScheduleAdapter friAdapter = new WeeklyScheduleAdapter(friList);
                friAdapter.setOnRvItemClickListener(this);
                friRv.setAdapter(friAdapter);
            } else {
                friRv.setVisibility(View.GONE);
                friNoPlan.setVisibility(View.VISIBLE);
            }

        } catch (ParseException e) {
            Log.e(TAG, "events: " + e.toString());
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

    private int getDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) return 0;
        return c.get(Calendar.DAY_OF_WEEK);
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

    @Override
    public void onResume() {
        super.onResume();

        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(getContext());
        }

        start = Hawk.get(Config.START_TIME_KEY);
        end = Hawk.get(Config.END_TIME_KEY);

        if (StringUtil.hasText(start)) {
            getEvents(start, end);
            return;
        }

        int fSize = getFragmentManager().getFragments().size();
        Fragment fragment = getFragmentManager().getFragments().get(fSize - 1);
        if (fragment instanceof DailyScheduleFragment || fragment instanceof WeeklyScheduleFragment) {
            PersianDate pd = new PersianDate();
            String[] periods = getFirstAndLastWeekDate(pd.getGrgYear(), pd.getGrgMonth(), pd.getGrgDay());
            getEvents(periods[0], periods[1]);
        }
    }

}
