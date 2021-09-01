package ir.madreseplus.ui.view.task;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.data.model.req.Task;
import ir.madreseplus.ui.base.BaseViewModel;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class TaskViewModel extends BaseViewModel<TaskActivityNavigator> {


    private MutableLiveData<Task> tasksLiveData;

    public TaskViewModel(DataManager mDataManager, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
        tasksLiveData = new MutableLiveData<>();
    }

    public LiveData<Task> getTasks() {
        return tasksLiveData;
    }

    public void getEventById(int id) {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().getTaskById(id)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(tasks -> {
                            setIsLoading(false);
                            tasksLiveData.postValue(tasks);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }

    public void setTask(int id) {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().setTaskDone(id)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(setTaskRes -> {
                            setIsLoading(false);
                            getNavigator().taskDone();
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }
}
