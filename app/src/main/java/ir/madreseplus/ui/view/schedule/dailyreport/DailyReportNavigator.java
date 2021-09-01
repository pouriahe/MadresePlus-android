package ir.madreseplus.ui.view.schedule.dailyreport;

import ir.madreseplus.data.model.res.DailyReportRes;
import ir.madreseplus.utilities.BaseNavigator;

public interface DailyReportNavigator extends BaseNavigator {

    void error(Throwable throwable);

    void dailyReportStateRes(DailyReportRes dailyReport);

    void dailyReportSubmitRes(DailyReportRes dailyReportRes);
}
