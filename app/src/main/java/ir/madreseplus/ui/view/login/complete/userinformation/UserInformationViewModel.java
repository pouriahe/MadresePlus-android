package ir.madreseplus.ui.view.login.complete.userinformation;

import androidx.lifecycle.MutableLiveData;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.data.model.entity.Student;
import ir.madreseplus.data.model.req.User;
import ir.madreseplus.ui.base.BaseViewModel;
import ir.madreseplus.ui.view.login.LoginNavigator;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class UserInformationViewModel extends BaseViewModel<UserInformationNavigator> {
    private static final String TAG = UserInformationViewModel.class.getSimpleName();


    public UserInformationViewModel(DataManager mDataManager, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }


    public void getProvinces() {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().getProvinces()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(provinceRes -> {
                            setIsLoading(false);
                            getNavigator().provinceRes(provinceRes);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }

    public void getCities(int id) {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().getCities(id)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(cityRes -> {
                            setIsLoading(false);
                            getNavigator().cityRes(cityRes);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }

}
