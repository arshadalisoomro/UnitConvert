package com.nimble.unitconvert.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "unit_table",
        //For speeding up queries
        indices = {@Index("category_id")},
        foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "category_id" ))
public class Unit {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "unit_name")
    private String unitName;

    @ColumnInfo(name = "unit_rate")
    private double unitRate;

    @ColumnInfo(name = "category_id")
    private long categoryId;

    public Unit(String unitName, double unitRate, long categoryId) {
        this.unitName = unitName;
        this.unitRate = unitRate;
        this.categoryId = categoryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public double getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(double unitRate) {
        this.unitRate = unitRate;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
