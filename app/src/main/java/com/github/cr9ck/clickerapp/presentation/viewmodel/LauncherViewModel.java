package com.github.cr9ck.clickerapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.cr9ck.clickerapp.model.repository.ApiRepository;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.Single;
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

    private LiveData<ViewState> getViewState() {
        Single.concat(apiRepository.getApiRequest(), apiRepository.getApiRequest())
                .subscribeOn(Schedulers.io())
                .firstElement()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        viewState.setValue(aBoolean ? ViewState.WEB : ViewState.GAME);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        viewState.setValue(ViewState.ERROR);
                    }
                });
        return viewState;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    public enum ViewState {
        WEB,
        GAME,
        ERROR
    }
}
