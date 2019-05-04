package com.nimble.unitconvert.view.model.interfaces;

import androidx.lifecycle.LiveData;

import com.nimble.unitconvert.model.Category;

import java.util.List;

public interface CategoryCrud extends BaseCrud<Category> {
    LiveData<List<Category>> getAll();
}
