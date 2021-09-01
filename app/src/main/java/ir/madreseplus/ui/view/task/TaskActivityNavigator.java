package ir.madreseplus.ui.view.task;

import ir.madreseplus.utilities.BaseNavigator;

public interface TaskActivityNavigator extends BaseNavigator {

    void error(Throwable throwable);

    void taskDone();


//    void getEventById(List<Task> tasks);
}
