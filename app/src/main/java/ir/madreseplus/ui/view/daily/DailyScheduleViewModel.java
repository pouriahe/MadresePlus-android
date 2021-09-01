package ir.madreseplus.ui.view.daily;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.ui.base.BaseViewModel;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class DailyScheduleViewModel extends BaseViewModel<DailyScheduleNavigator> {

    public DailyScheduleViewModel(DataManager mDataManager, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }


    public void events(String start, String end) {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().events(start, end)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(events -> {
                            setIsLoading(false);
                            getNavigator().events(events);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }

}
