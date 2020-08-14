package com.github.cr9ck.clickerapp.model.repository;

import com.github.cr9ck.clickerapp.model.network.ServerApi;

import javax.inject.Inject;

import io.reactivex.Single;

public class ApiRepositoryImpl implements ApiRepository {

    private ServerApi serverApi;

    @Inject
    public ApiRepositoryImpl(ServerApi serverApi) {
        this.serverApi = serverApi;
    }

    @Override
    public Single<Boolean> getApiRequest() {
        return serverApi.getResult();
    }
}
