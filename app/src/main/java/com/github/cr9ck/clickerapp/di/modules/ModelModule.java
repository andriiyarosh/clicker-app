package com.github.cr9ck.clickerapp.di.modules;

import com.github.cr9ck.clickerapp.model.network.ApiService;
import com.github.cr9ck.clickerapp.model.network.ServerApi;
import com.github.cr9ck.clickerapp.model.repository.ApiRepository;
import com.github.cr9ck.clickerapp.model.repository.ApiRepositoryImpl;

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
    public ApiRepository provideApiRepository(ServerApi serverApi) {
        return new ApiRepositoryImpl(serverApi);
    }

    @Provides
    public CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }
}
