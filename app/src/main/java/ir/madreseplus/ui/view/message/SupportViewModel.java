package ir.madreseplus.ui.view.message;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.ui.base.BaseViewModel;
import ir.madreseplus.ui.view.daily.DailyScheduleNavigator;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class SupportViewModel extends BaseViewModel<SupportNavigator> {

    public SupportViewModel(DataManager mDataManager, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }


    public void getTickets() {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().getTickets()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(ticketRes -> {
                            setIsLoading(false);
                            getNavigator().tickets(ticketRes);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }

}
