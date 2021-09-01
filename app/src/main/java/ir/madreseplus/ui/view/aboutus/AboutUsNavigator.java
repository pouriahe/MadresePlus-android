package ir.madreseplus.ui.view.aboutus;

import java.util.List;

import ir.madreseplus.data.model.req._Content;
import ir.madreseplus.utilities.BaseNavigator;

public interface AboutUsNavigator<T> extends BaseNavigator {

    void error(Throwable throwable);


    void contents(_Content contents);

}
