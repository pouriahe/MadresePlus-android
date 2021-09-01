package ir.madreseplus.ui.view.practice;

import ir.madreseplus.utilities.BaseNavigator;

public interface PracticeActivityNavigator extends BaseNavigator {

    void error(Throwable throwable);

    void practiceDone();

}
