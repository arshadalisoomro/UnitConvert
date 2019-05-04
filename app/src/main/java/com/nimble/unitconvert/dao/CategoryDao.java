package com.nimble.unitconvert.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nimble.unitconvert.model.Category;
import com.nimble.unitconvert.model.Unit;

import java.util.List;

@Dao
public interface CategoryDao extends BaseDao<Category> {

    @Insert
    void insertUnitList(List<Unit> unitList);

    @Query("SELECT * FROM category_table WHERE id = :categoryId")
    Category getCategoryById(long categoryId);

    @Query("DELETE FROM category_table")
    void deleteAllCategories();

    @Query("SELECT * FROM category_table ORDER BY category_name")
    LiveData<List<Category>> getAllCategories();
}
