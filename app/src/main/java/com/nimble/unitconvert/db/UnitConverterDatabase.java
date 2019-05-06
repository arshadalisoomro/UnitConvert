package com.nimble.unitconvert.db;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.nimble.unitconvert.dao.CategoryDao;
import com.nimble.unitconvert.dao.UnitDao;
import com.nimble.unitconvert.model.Category;
import com.nimble.unitconvert.model.Unit;

import static com.nimble.unitconvert.util.Constant.Acceleration.*;
import static com.nimble.unitconvert.util.Constant.Angle.*;
import static com.nimble.unitconvert.util.Constant.Area.*;
import static com.nimble.unitconvert.util.Constant.Data.*;

@Database(entities = {Category.class, Unit.class}, version = 1)
public abstract class UnitConverterDatabase extends RoomDatabase {

    private static final String TAG = UnitConverterDatabase.class.getSimpleName();

    private static UnitConverterDatabase instance;

    public abstract CategoryDao categoryDao();

    public abstract UnitDao unitDao();

    public static synchronized UnitConverterDatabase getInstance(Context context){

        Log.e(TAG, "Initiated...");

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

            //Insert Categories and Units
            insertAcceleration(categoryDao, unitDao);
            insertAngle(categoryDao, unitDao);
            insertArea(categoryDao, unitDao);
            insertData(categoryDao, unitDao);

            return null;
        }
    }

    private static void insertAcceleration(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_ACCELERATION));

        unitDao.insert(new Unit(UNIT_METER_PER_SQUARE_SECOND, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_INCH_PER_SQUARE_SECOND, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_GRAVITY, 0.0, catRowId));

    }

    private static void insertAngle(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_ANGLE));

        unitDao.insert(new Unit(UNIT_DEGREE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_RADIAN, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_GRAD, 0.0, catRowId));

    }

    private static void insertArea(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_AREA));

        unitDao.insert(new Unit(UNIT_SQUARE_KILOMETER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_METER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_CENTIMETER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_MILLIMETER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_MICROMETER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_NANOMETER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_ANGSTROM, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_PICO_METER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_FEMTO_METER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_INCH, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_FOOT, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_HECTARE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_ACRE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_ARES, 0.0, catRowId));

    }

    private static void insertData(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_DATA));

        unitDao.insert(new Unit(UNIT_BIT, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_KILO_BIT, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MEGA_BIT, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_GIGA_BIT, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_TERA_BIT, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_BYTE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_KILO_BYTE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MEGA_BYTE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_GIGA_BYTE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_TERA_BYTE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_PETA_BYTE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_EXA_BYTE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_ZETTA_BYTE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_YOTTA_BYTE, 0.0, catRowId));

    }



}
