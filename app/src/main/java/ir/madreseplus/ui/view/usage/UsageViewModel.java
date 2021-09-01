package ir.madreseplus.ui.view.usage;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.ui.base.BaseViewModel;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class UsageViewModel extends BaseViewModel<UsageNavigator> {

    public UsageViewModel(DataManager mDataManager, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }


    public void usage() {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().usageInstructions()
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
