package com.nimble.unitconvert.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.nimble.unitconvert.dao.CategoryDao;
import com.nimble.unitconvert.db.UnitConverterDatabase;
import com.nimble.unitconvert.model.Category;
import com.nimble.unitconvert.model.Unit;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CategoryRepository implements CategoryDao {

    private CategoryDao categoryDao;
    private LiveData<List<Category>> allCategories;

    public CategoryRepository(Application application){
        UnitConverterDatabase database = UnitConverterDatabase.getInstance(application);
        categoryDao = database.categoryDao();
        allCategories = categoryDao.getAllCategories();
    }

    @Override
    public long insert(Category category) {
        long lastInsertedId = 0;
        try {
            lastInsertedId = new InsertCategoryAsyncTask(categoryDao).execute(category).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return lastInsertedId;
    }

    @Override
    public void update(Category category) {
        new UpdateCategoryAsyncTask(categoryDao).execute(category);
    }

    @Override
    public void delete(Category category) {
        new DeleteCategoryAsyncTask(categoryDao).execute(category);
    }

    @Override
    public void insertUnitList(List<Unit> unitList) {
        new InsertUnitListAsyncTask(categoryDao).execute(unitList);
    }

    @Override
    public Category getCategoryById(long categoryId) {
        Category category = null;

        try {
            category = new SelectCategoryByIdAsyncTask(categoryDao).execute(categoryId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public void deleteAllCategories() {
        new DeleteAllCategoriesAsyncTask(categoryDao).execute();
    }

    @Override
    public LiveData<List<Category>> getAllCategories() {
        return allCategories;
    }

    private static class SelectCategoryByIdAsyncTask extends AsyncTask<Long, Void, Category>{

        private CategoryDao categoryDao;

        public SelectCategoryByIdAsyncTask(CategoryDao categoryDao){
            this.categoryDao = categoryDao;
        }

        @Override
        protected Category doInBackground(Long... longs) {
            return categoryDao.getCategoryById(longs[0]);
        }
    }

    private static class InsertCategoryAsyncTask extends AsyncTask<Category, Void, Long>{

        private CategoryDao categoryDao;

        public InsertCategoryAsyncTask(CategoryDao categoryDao){
            this.categoryDao = categoryDao;
        }

        @Override
        protected Long doInBackground(Category... categories) {
            return categoryDao.insert(categories[0]);
        }
    }

    private static class InsertUnitListAsyncTask extends AsyncTask<List<Unit>, Void, Void>{

        private CategoryDao categoryDao;

        private InsertUnitListAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<Unit>... lists) {
            categoryDao.insertUnitList(lists[0]);
            return null;
        }
    }

    private static class UpdateCategoryAsyncTask extends AsyncTask<Category, Void, Void>{

        private CategoryDao categoryDao;

        public UpdateCategoryAsyncTask(CategoryDao categoryDao){
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.update(categories[0]);
            return null;
        }
    }

    private static class DeleteCategoryAsyncTask extends AsyncTask<Category, Void, Void>{

        private CategoryDao categoryDao;

        public DeleteCategoryAsyncTask(CategoryDao categoryDao){
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.delete(categories[0]);
            return null;
        }
    }

    private static class DeleteAllCategoriesAsyncTask extends AsyncTask<Void, Void, Void>{

        private CategoryDao categoryDao;

        public DeleteAllCategoriesAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            categoryDao.deleteAllCategories();
            return null;
        }
    }
}
