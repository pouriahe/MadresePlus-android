package ir.madreseplus.ui.view.main;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.ui.base.BaseViewModel;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class MainFViewModel extends BaseViewModel<MainFNavigator> {

    public MainFViewModel(DataManager mDataManager, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }

    public void home() {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().home()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(dashboard -> {
                            setIsLoading(false);
                            getNavigator().home(dashboard);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }

    public void profile(){
        getCompositeDisposable().add(
                getDataManager().getStudent()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(studentList -> {
                            setIsLoading(false);
                            getNavigator().profile(studentList);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }
}
