package ir.madreseplus.ui.view.login;


import ir.madreseplus.data.DataManager;
import ir.madreseplus.data.model.entity.Student;
import ir.madreseplus.data.model.req.User;
import ir.madreseplus.ui.base.BaseViewModel;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class LoginViewModel extends BaseViewModel<LoginNavigator> {
    private static final String TAG = "LoginViewModel";


    public LoginViewModel(DataManager mDataManager, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }


    public void login(User user) {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().login(user)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(user1 -> {
                            setIsLoading(false);
                            getNavigator().login(user1);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }

    public void password(User user) {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().password(user)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(user1 -> {
                            setIsLoading(false);
                            getNavigator().password(user1);
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
                            getNavigator().profile(student);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }

    public void insertStudent(Student student) {
        setIsLoading(false);
//

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

    public void confirmCode(User user, String sessionId) {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().confirm(user, sessionId)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(student -> {
                            setIsLoading(false);
                            getNavigator().confirmResult(student);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }
}
