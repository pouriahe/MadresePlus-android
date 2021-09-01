package ir.madreseplus.ui.view.podcast;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.ui.base.BaseViewModel;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class PodcastViewModel extends BaseViewModel<PodcastNavigator> {

    public PodcastViewModel(DataManager mDataManager, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }

    public void podcast(int page, String category) {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().podcast(page, category)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(podcasts -> {
                            setIsLoading(false);
                            getNavigator().podcasts(podcasts);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }
}
