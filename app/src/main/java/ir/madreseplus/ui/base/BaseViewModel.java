package ir.madreseplus.ui.base;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import ir.madreseplus.data.DataManager;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class BaseViewModel<N> extends ViewModel {

    private DataManager mDataManager;

    ObservableBoolean mIsLoading = new ObservableBoolean();

    private CompositeDisposable mCompositeDisposable;

    private SchedulerProvider mSchedulerProvider;

    private WeakReference<N> mNavigator;

    public BaseViewModel(DataManager mDataManager, SchedulerProvider mSchedulerProvider) {
        this.mDataManager = mDataManager;
        this.mSchedulerProvider = mSchedulerProvider;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.clear();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public ObservableBoolean getmIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    public N getNavigator() {
        return mNavigator.get();
    }


}
