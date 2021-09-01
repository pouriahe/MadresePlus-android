package ir.madreseplus.ui.view.aricle;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.ui.base.BaseViewModel;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class ArticleViewModel extends BaseViewModel<ArticleNavigator> {

    public ArticleViewModel(DataManager mDataManager, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }


 /*   public void content() {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().content()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(contents -> {
                            setIsLoading(false);
                            getNavigator().contents(contents);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }


    public void podcast() {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().podcast()
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
    }*/
}
