package com.nimble.unitconvert.view.model.interfaces;

import androidx.lifecycle.LiveData;

import com.nimble.unitconvert.model.Unit;

import java.util.List;

public interface UnitCrud extends BaseCrud<Unit> {
    LiveData<List<Unit>> getAll(long catId);
}
