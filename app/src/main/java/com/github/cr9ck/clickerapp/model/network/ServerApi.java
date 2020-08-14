package com.github.cr9ck.clickerapp.model.network;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ServerApi {

    @GET("getResult")
    Single<Boolean> getResult();
}
