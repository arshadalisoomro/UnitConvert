package com.nimble.unitconvert.view.model.interfaces;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface BaseCrud<T> {
    long insert(T t);
    void update(T t);
    void delete(T t);

    T getById(long id);
    void deleteById(long id);
    void deleteAll();
}
