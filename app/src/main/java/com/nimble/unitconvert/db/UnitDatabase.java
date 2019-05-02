package com.nimble.unitconvert.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.nimble.unitconvert.dao.UnitDao;
import com.nimble.unitconvert.model.Unit;

@Database(entities = {Unit.class}, version = 1)
public abstract class UnitDatabase extends RoomDatabase {

    private static UnitDatabase instance;

    public abstract UnitDao unitDao();

    public static synchronized UnitDatabase getInstance(Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UnitDatabase.class, "unit_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDefaultUnitsAsyncTask(instance).execute();
        }
    };

    private static class PopulateDefaultUnitsAsyncTask extends AsyncTask<Void, Void, Void>{

        private UnitDao unitDao;

        private PopulateDefaultUnitsAsyncTask(UnitDatabase unitDatabase){
            this.unitDao = unitDatabase.unitDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            unitDao.insertUnit(new Unit("Celsius", 32.0));
            return null;
        }
    }

}
