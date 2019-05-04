package com.nimble.unitconvert.view.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nimble.unitconvert.model.Category;
import com.nimble.unitconvert.repository.CategoryRepository;
import com.nimble.unitconvert.view.model.interfaces.BaseCrud;
import com.nimble.unitconvert.view.model.interfaces.CategoryCrud;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel implements CategoryCrud {

    private CategoryRepository categoryRepository;
    private LiveData<List<Category>> allCategories;

    public CategoryViewModel(@NonNull Application application) {
        super(application);

        categoryRepository = new CategoryRepository(application);
        allCategories = categoryRepository.getAllCategories();

    }

    @Override
    public long insert(Category category) {
        return categoryRepository.insert(category);
    }

    @Override
    public void update(Category category) {
        categoryRepository.update(category);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public Category getById(long id) {
        return categoryRepository.getCategoryById(id);
    }

    @Override
    public void deleteById(long id) {
        //categoryRepository.
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAllCategories();
    }

    @Override
    public LiveData<List<Category>> getAll() {
        return allCategories;
    }
}
