package ir.madreseplus.ui.view.schedule.dailyreport;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.data.model.req.DailyReportReq;
import ir.madreseplus.ui.base.BaseViewModel;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class DailyReportViewModel extends BaseViewModel<DailyReportNavigator> {

    public DailyReportViewModel(DataManager mDataManager, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }



    public void getDailyReportState() {
        getCompositeDisposable().add(
                getDataManager().getDailyReportReportState()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(dailyReportRes -> {
                            setIsLoading(false);
                            getNavigator().dailyReportStateRes(dailyReportRes);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }

    public void submitDailyReport(DailyReportReq dailyReportReq) {
        getCompositeDisposable().add(
                getDataManager().submitReport(dailyReportReq)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(dailyReportRes -> {
                            setIsLoading(false);
                            getNavigator().dailyReportSubmitRes(dailyReportRes);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }
}
