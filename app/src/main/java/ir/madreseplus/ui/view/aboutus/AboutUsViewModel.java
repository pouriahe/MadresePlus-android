package ir.madreseplus.ui.view.aboutus;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.ui.base.BaseViewModel;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class AboutUsViewModel extends BaseViewModel<AboutUsNavigator> {

    public AboutUsViewModel(DataManager mDataManager, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }


    public void aboutUS() {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().aboutUs()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(content -> {
                            setIsLoading(false);
                            getNavigator().contents(content);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }

}
