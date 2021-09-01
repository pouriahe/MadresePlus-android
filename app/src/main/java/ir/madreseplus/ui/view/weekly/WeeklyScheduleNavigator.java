package ir.madreseplus.ui.view.weekly;

import java.util.List;

import ir.madreseplus.data.model.req.Event;
import ir.madreseplus.utilities.BaseNavigator;

public interface WeeklyScheduleNavigator<T> extends BaseNavigator {

    void error(Throwable throwable);

    void events(List<Event> events);

}
