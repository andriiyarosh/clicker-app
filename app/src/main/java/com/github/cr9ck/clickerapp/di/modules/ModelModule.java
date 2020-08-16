package com.github.cr9ck.clickerapp.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

import com.github.cr9ck.clickerapp.R;
import com.github.cr9ck.clickerapp.model.network.ApiService;
import com.github.cr9ck.clickerapp.model.network.ServerApi;
import com.github.cr9ck.clickerapp.model.repository.ApiRepository;
import com.github.cr9ck.clickerapp.model.repository.ApiRepositoryImpl;
import com.github.cr9ck.clickerapp.presentation.view.game.levels.Level;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ModelModule {

    @Provides
    @Singleton
    public ServerApi provideServerApi() {
        return ApiService.buildService(ServerApi.class);
    }

    @Provides
    public ApiRepository provideApiRepository(ServerApi serverApi, SharedPreferences preferences, @Named(value = "stateKey") String stateKey) {
        return new ApiRepositoryImpl(serverApi, preferences, stateKey);
    }

    @Provides
    public CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    public SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    @Provides
    @Named(value = "stateKey")
    public String provideSate(Context context) {
        return context.getString(R.string.key_saved_state);
    }
}
