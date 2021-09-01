package ir.madreseplus.ui.view.daily;

import java.util.List;

import ir.madreseplus.data.model.req.Event;
import ir.madreseplus.utilities.BaseNavigator;

public interface DailyScheduleNavigator<T> extends BaseNavigator {

    void error(Throwable throwable);

    void events(List<Event> events);

}
