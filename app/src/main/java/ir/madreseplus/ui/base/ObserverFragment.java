package ir.madreseplus.ui.base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class ObserverFragment extends BaseFragment {
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected boolean isSubscribeCall = false;

    @Override
    public void onStart() {
        super.onStart();
        if (!isSubscribeCall) {
            subscribe();
            isSubscribeCall = true;
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        unSubscribe();
    }

    public abstract void subscribe();

    public void unSubscribe() {
        compositeDisposable.clear();
    }
}
