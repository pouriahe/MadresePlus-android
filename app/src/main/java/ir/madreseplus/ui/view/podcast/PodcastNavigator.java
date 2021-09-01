package ir.madreseplus.ui.view.podcast;

import java.util.List;

import ir.madreseplus.data.model.req.Podcast;
import ir.madreseplus.utilities.BaseNavigator;

public interface PodcastNavigator<T> extends BaseNavigator {

    void error(Throwable throwable);

    void podcasts(List<Podcast> podcasts);
}
