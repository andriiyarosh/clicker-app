package com.github.cr9ck.clickerapp.model.repository;

import io.reactivex.Single;

public interface ApiRepository {
    Single<Boolean> getApiRequest();
}
