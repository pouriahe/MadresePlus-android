package ir.madreseplus.ui.view.practice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.data.model.req.Practice;
import ir.madreseplus.data.model.req.Task;
import ir.madreseplus.ui.base.BaseViewModel;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class PracticeViewModel extends BaseViewModel<PracticeActivityNavigator> {


    private MutableLiveData<Task> practicesLiveData;

    public PracticeViewModel(DataManager mDataManager, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
        practicesLiveData = new MutableLiveData<>();
    }

    public LiveData<Task> getTasks() {
        return practicesLiveData;
    }

    public void getPracticeById(int id) {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().getTaskById(id)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(task -> {
                            setIsLoading(false);
                            practicesLiveData.postValue(task);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }

    public void setPracticeResult(int id, int correct, int wrong, int noAnswered) {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().setTaskResult( id, correct, wrong, noAnswered)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(setTaskRes -> {
                            setIsLoading(false);
                            getNavigator().practiceDone();
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }
}
