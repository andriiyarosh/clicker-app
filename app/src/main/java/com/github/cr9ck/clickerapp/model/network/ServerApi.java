package com.github.cr9ck.clickerapp.model.network;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ServerApi {

    @GET("getResult")
    Observable<Boolean> getResult();
}
