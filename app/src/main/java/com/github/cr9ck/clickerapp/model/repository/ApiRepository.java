package com.github.cr9ck.clickerapp.model.repository;

import io.reactivex.Single;

public interface ApiRepository {
    Single<Integer> getSavedState();
    void saveState(Integer stateOrdinal);
}
