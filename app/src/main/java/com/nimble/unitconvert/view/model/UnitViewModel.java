package com.nimble.unitconvert.view.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nimble.unitconvert.model.Unit;
import com.nimble.unitconvert.repository.UnitRepository;
import com.nimble.unitconvert.view.model.interfaces.BaseCrud;
import com.nimble.unitconvert.view.model.interfaces.UnitCrud;

import java.util.List;

public class UnitViewModel extends AndroidViewModel implements UnitCrud {

    private UnitRepository unitRepository;
    //private LiveData<List<Unit>> allUnits;

    public UnitViewModel(@NonNull Application application) {
        super(application);
        unitRepository = new UnitRepository(application);
        //allUnits = unitRepository.getAllUnits(catId);
    }

    @Override
    public long insert(Unit unit) {
        return unitRepository.insert(unit);
    }

    @Override
    public void update(Unit unit) {
        unitRepository.update(unit);
    }

    @Override
    public void delete(Unit unit) {
        unitRepository.delete(unit);
    }

    @Override
    public Unit getById(long id) {
        return unitRepository.getUnitById(id);
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void deleteAll() {
        unitRepository.deleteAllUnits();
    }

    @Override
    public LiveData<List<Unit>> getAll(long catId) {
        return unitRepository.getAllUnits(catId);
    }
}
