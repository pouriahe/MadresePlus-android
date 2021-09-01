package ir.madreseplus.ui.view.usage;

import java.util.List;

import ir.madreseplus.data.model.req._Content;
import ir.madreseplus.utilities.BaseNavigator;

public interface UsageNavigator<T> extends BaseNavigator {

    void error(Throwable throwable);


    void contents(_Content content);

}
