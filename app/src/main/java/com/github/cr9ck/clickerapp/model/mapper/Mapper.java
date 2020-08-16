package com.github.cr9ck.clickerapp.model.mapper;

import java.util.List;

public interface Mapper<M, E> {
    E mapToEntity(M model);
    M mapToModel(E model);
    List<E> mapToEntity(List<M> model);
    List<M> mapToModel(List<E> model);
}
