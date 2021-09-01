package ir.madreseplus.ui.view.content;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.ui.base.BaseViewModel;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class ContentViewModel extends BaseViewModel<ContentNavigator> {

    public ContentViewModel(DataManager mDataManager, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }


    public void content(int page, String category) {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().content(page, category)
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

}
