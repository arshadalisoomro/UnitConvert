package com.nimble.unitconvert.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.nimble.unitconvert.dao.CategoryDao;
import com.nimble.unitconvert.dao.UnitDao;
import com.nimble.unitconvert.model.Category;
import com.nimble.unitconvert.model.Unit;

@Database(entities = {Unit.class}, version = 1)
public abstract class UnitConverterDatabase extends RoomDatabase {

    private static UnitConverterDatabase instance;

    public abstract CategoryDao categoryDao();

    public abstract UnitDao unitDao();

    public static synchronized UnitConverterDatabase getInstance(Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UnitConverterDatabase.class, "unit_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    //Init and insert data in Database
    private static RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDefaultUnitsAsyncTask(instance).execute();
        }
    };

    private static class PopulateDefaultUnitsAsyncTask extends AsyncTask<Void, Void, Void>{

        private CategoryDao categoryDao;
        private UnitDao unitDao;

        private PopulateDefaultUnitsAsyncTask(UnitConverterDatabase database){
            this.categoryDao = database.categoryDao();
            this.unitDao = database.unitDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            //Insert Categories first
            categoryDao.insert(new Category("Length"));

            //unitDao.insert(new Unit("Celsius", 32.0));
            return null;
        }
    }

}
