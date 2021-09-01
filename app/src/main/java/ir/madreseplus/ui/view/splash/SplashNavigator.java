package ir.madreseplus.ui.view.splash;

import ir.madreseplus.data.model.res.Splash;
import ir.madreseplus.utilities.BaseNavigator;

public interface SplashNavigator<T> extends BaseNavigator {

    void error(Throwable throwable);

    void authCheck(Splash splash);
}
