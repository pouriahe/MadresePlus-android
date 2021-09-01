package ir.madreseplus.ui.view;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.ui.base.BaseViewModel;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class MainActivityViewModel extends BaseViewModel<MainActivityNavigator> {

    public MainActivityViewModel(DataManager mDataManager, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }



    public void getProfile() {
        getCompositeDisposable().add(
                getDataManager().getStudent()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(studentList -> {
                            setIsLoading(false);
                            getNavigator().getProfile(studentList);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }
}
