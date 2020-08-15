package com.github.cr9ck.clickerapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.cr9ck.clickerapp.model.repository.ApiRepository;
import com.github.cr9ck.clickerapp.presentation.view.ViewState;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LauncherViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable;
    private ApiRepository apiRepository;
    private MutableLiveData<ViewState> viewState = new MutableLiveData<>();
    public LiveData<ViewState> launcherViewState;

    @Inject
    public LauncherViewModel(ApiRepository apiRepository, CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
        this.apiRepository = apiRepository;
        launcherViewState = getViewState();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    private LiveData<ViewState> getViewState() {
        apiRepository.getSavedState()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Integer stateOrdinal) {
                        viewState.setValue(ViewState.values()[stateOrdinal]);
                        apiRepository.saveState(stateOrdinal);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        viewState.setValue(ViewState.ERROR);
                        apiRepository.saveState(ViewState.ERROR.ordinal());
                    }
                });
        return viewState;
    }
}
