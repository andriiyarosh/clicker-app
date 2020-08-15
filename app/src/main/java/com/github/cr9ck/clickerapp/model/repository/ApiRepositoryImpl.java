package com.github.cr9ck.clickerapp.model.repository;

import android.content.SharedPreferences;

import com.github.cr9ck.clickerapp.model.network.ServerApi;
import com.github.cr9ck.clickerapp.presentation.view.ViewState;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class ApiRepositoryImpl implements ApiRepository {

    private ServerApi serverApi;
    private SharedPreferences preferences;
    private final String stateKey;

    @Inject
    public ApiRepositoryImpl(ServerApi serverApi, SharedPreferences preferences, String stateKey) {
        this.serverApi = serverApi;
        this.preferences = preferences;
        this.stateKey = stateKey;
    }

    @Override
    public Single<Integer> getSavedState() {
        Observable<Integer> locallySavedState = Observable.just(preferences.getInt(stateKey, ViewState.ERROR.ordinal()));
        Observable<Integer> apiState = serverApi.getResult().map(aBoolean -> aBoolean ? ViewState.WEB.ordinal() : ViewState.GAME.ordinal());
        return Observable.concat(locallySavedState, apiState)
                .filter(result -> result != ViewState.ERROR.ordinal())
                .first(ViewState.ERROR.ordinal());
    }

    @Override
    public void saveState(Integer stateOrdinal) {
        preferences.edit().putInt(stateKey, stateOrdinal).apply();
    }
}
