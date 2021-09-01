package ir.madreseplus.ui.view.content;

import java.util.List;

import ir.madreseplus.data.model.req._Content;
import ir.madreseplus.utilities.BaseNavigator;

public interface ContentNavigator<T> extends BaseNavigator {

    void error(Throwable throwable);


    void contents(List<_Content> contents);

}
