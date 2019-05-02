package com.nimble.unitconvert.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Data
@Entity(tableName = "unit_table")
public class Unit {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "unit_name")
    private String unitName;

    @ColumnInfo(name = "unit_rate")
    private double unitRate;

    public Unit(String unitName, double unitRate) {
        this.unitName = unitName;
        this.unitRate = unitRate;
    }



}
