package ir.madreseplus.ui.view.aricle;

import java.util.List;

import ir.madreseplus.data.model.req._Content;
import ir.madreseplus.data.model.req.Podcast;
import ir.madreseplus.utilities.BaseNavigator;

public interface ArticleNavigator<T> extends BaseNavigator {

    void error(Throwable throwable);


    void contents(List<_Content> contents);

    void podcasts(List<Podcast> contents);
}
