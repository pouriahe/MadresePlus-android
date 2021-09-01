package ir.madreseplus.ui.view.login.complete;

import androidx.lifecycle.MutableLiveData;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.data.model.entity.Student;
import ir.madreseplus.data.model.req.User;
import ir.madreseplus.ui.base.BaseViewModel;
import ir.madreseplus.ui.view.login.LoginNavigator;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class CompleteRegisterViewModel extends BaseViewModel<CompleteRegisterNavigator> {
    private static final String TAG = "LoginViewModel";


    public CompleteRegisterViewModel(DataManager mDataManager, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }

    public void insertStudent(Student student) {
        setIsLoading(false);
        getCompositeDisposable().add(
                getDataManager().insertStudent(student)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(() -> {
                            setIsLoading(false);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }

    public void completeRegister(User user) {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().completeRegister(user)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(complementRes -> {
                            setIsLoading(false);
                            getNavigator().complementResult(complementRes);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }

    public void getProfile() {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().profile()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(student -> {
                            setIsLoading(false);
                            getNavigator().profileResult(student);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }
}
