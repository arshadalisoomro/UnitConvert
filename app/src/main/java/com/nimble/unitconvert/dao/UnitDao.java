package com.nimble.unitconvert.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nimble.unitconvert.model.Unit;

import java.util.List;

@Dao
public interface UnitDao extends BaseDao<Unit>{

    @Query("SELECT * FROM unit_table WHERE id = :unitId")
    Unit getUnitById(long unitId);

    @Query("DELETE FROM unit_table")
    void deleteAllUnits();

    @Query("SELECT * FROM unit_table ORDER BY unit_name")
    LiveData<List<Unit>> getAllUnits();

    @Query("SELECT unit_rate FROM unit_table WHERE unit_name = :unitName")
    double getUnitRateByUnitName(String unitName);

}
