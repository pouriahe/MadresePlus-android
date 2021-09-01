package ir.madreseplus.ui.view.splash;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.ui.base.BaseViewModel;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class SplashViewModel extends BaseViewModel<SplashNavigator> {
    private static final String TAG = "LoginViewModel";


    public SplashViewModel(DataManager mDataManager, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }


    public void authChecker() {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().authChecker()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(splash -> {
                            setIsLoading(false);
                            getNavigator().authCheck(splash);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }

}
