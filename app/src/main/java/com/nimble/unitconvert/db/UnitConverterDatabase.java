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
import static com.nimble.unitconvert.util.Constant.Current.*;
import static com.nimble.unitconvert.util.Constant.Data.*;
import static com.nimble.unitconvert.util.Constant.ElectricCharge.*;
import static com.nimble.unitconvert.util.Constant.Energy.*;
import static com.nimble.unitconvert.util.Constant.Force.*;
import static com.nimble.unitconvert.util.Constant.Humidity.*;
import static com.nimble.unitconvert.util.Constant.Length.*;
import static com.nimble.unitconvert.util.Constant.Luminance.*;
import static com.nimble.unitconvert.util.Constant.LuminousFlux.*;
import static com.nimble.unitconvert.util.Constant.Mass.*;
import static com.nimble.unitconvert.util.Constant.Pressure.*;
import static com.nimble.unitconvert.util.Constant.Speed.*;
import static com.nimble.unitconvert.util.Constant.Temperature.*;
import static com.nimble.unitconvert.util.Constant.TemperatureGradient.*;
import static com.nimble.unitconvert.util.Constant.Time.*;
import static com.nimble.unitconvert.util.Constant.Torque.*;
import static com.nimble.unitconvert.util.Constant.Voltage.*;
import static com.nimble.unitconvert.util.Constant.Volume.*;
import static com.nimble.unitconvert.util.Constant.Work.*;

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
            insertCurrent(categoryDao, unitDao);
            insertElectricCharge(categoryDao, unitDao);
            insertEnergy(categoryDao, unitDao);
            insertForce(categoryDao, unitDao);
            insertHumidity(categoryDao, unitDao);
            insertLength(categoryDao, unitDao);
            insertLuminance(categoryDao, unitDao);
            insertLuminousFlux(categoryDao, unitDao);
            insertMass(categoryDao, unitDao);
            insertPressure(categoryDao, unitDao);
            insertSpeed(categoryDao, unitDao);
            insertTemperature(categoryDao, unitDao);
            insertTemperatureGradient(categoryDao, unitDao);
            insertTime(categoryDao, unitDao);
            insertTorque(categoryDao, unitDao);
            insertVoltage(categoryDao, unitDao);
            insertVolume(categoryDao, unitDao);
            insertWork(categoryDao, unitDao);

            return null;
        }
    }

    private static void insertAcceleration(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_ACCELERATION));

        unitDao.insert(new Unit(UNIT_METER_PER_SQUARE_SECOND, 1.0E0, catRowId));
        unitDao.insert(new Unit(UNIT_INCH_PER_SQUARE_SECOND, 0.0254, catRowId));
        unitDao.insert(new Unit(UNIT_GRAVITY, 9.80665, catRowId));

    }

    private static void insertAngle(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_ANGLE));

        unitDao.insert(new Unit(UNIT_DEGREE, (Math.PI / 180.0), catRowId));
        unitDao.insert(new Unit(UNIT_RADIAN, 1.0, catRowId));
        unitDao.insert(new Unit(UNIT_GRAD, 0.9, catRowId));

    }

    private static void insertArea(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_AREA));

        unitDao.insert(new Unit(UNIT_SQUARE_KILOMETER, 1.0E6, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_METER, 1.0, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_CENTIMETER, 1.0E-4, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_MILLIMETER, 1.0E-6, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_MICROMETER, 1.0E-12, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_NANOMETER, 1.0E-18, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_ANGSTROM, 1.0E-20, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_PICO_METER, 1.0E-24, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_FEMTO_METER, 1.0E-30, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_INCH, 0.00064516, catRowId));
        unitDao.insert(new Unit(UNIT_SQUARE_FOOT, 0.09290304, catRowId));
        unitDao.insert(new Unit(UNIT_HECTARE, 1.0E5, catRowId));
        unitDao.insert(new Unit(UNIT_ACRE, 4046.8564224, catRowId));
        unitDao.insert(new Unit(UNIT_ARES, 100, catRowId));

    }

    private static void insertData(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_DATA));

        unitDao.insert(new Unit(UNIT_BIT, 1.0, catRowId));
        unitDao.insert(new Unit(UNIT_KILO_BIT, 1024.0, catRowId));
        unitDao.insert(new Unit(UNIT_MEGA_BIT, 1048576.0, catRowId));
        unitDao.insert(new Unit(UNIT_GIGA_BIT, 1073741824.0, catRowId));
        unitDao.insert(new Unit(UNIT_TERA_BIT, 1099511627776.0, catRowId));
        unitDao.insert(new Unit(UNIT_BYTE, 8.0, catRowId));
        unitDao.insert(new Unit(UNIT_KILO_BYTE, 8192.0, catRowId));
        unitDao.insert(new Unit(UNIT_MEGA_BYTE, 8388608.0, catRowId));
        unitDao.insert(new Unit(UNIT_GIGA_BYTE, 8.589934592E9, catRowId));
        unitDao.insert(new Unit(UNIT_TERA_BYTE, 8.796093E12, catRowId));
        unitDao.insert(new Unit(UNIT_PETA_BYTE, 33.86205821701E11, catRowId));
        unitDao.insert(new Unit(UNIT_EXA_BYTE, 9.223372037E18, catRowId));
        unitDao.insert(new Unit(UNIT_ZETTA_BYTE, 9.44473296573929E21, catRowId));
        unitDao.insert(new Unit(UNIT_YOTTA_BYTE, 8E24, catRowId));

    }

    private static void insertCurrent(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_CURRENT));

        unitDao.insert(new Unit(UNIT_AMPERE, 1.0, catRowId));
        unitDao.insert(new Unit(UNIT_KILO_AMPERE, 1.0E3, catRowId));
        unitDao.insert(new Unit(UNIT_MEGA_AMPERE, 1.0E6, catRowId));
        unitDao.insert(new Unit(UNIT_PICO_AMPERE, 1.0E-12, catRowId));
        unitDao.insert(new Unit(UNIT_NANO_AMPERE, 1.0E-9, catRowId));
        unitDao.insert(new Unit(UNIT_MICRO_AMPERE, 1.0E-6, catRowId));
        unitDao.insert(new Unit(UNIT_MILLI_AMPERE, 1.0E-3, catRowId));

    }

    private static void insertElectricCharge(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_ELECTRIC_CHARGE));

        unitDao.insert(new Unit(UNIT_ELEMENTARY_CHARGE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_COULOMB, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_PICO_COULOMB, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_NANO_COULOMB, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MICRO_COULOMB, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MILLI_COULOMB, 0.0, catRowId));

    }

    private static void insertEnergy(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_ENERGY));

        unitDao.insert(new Unit(UNIT_JOULE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_KILO_JOULE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MEGA_JOULE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MILLI_JOULE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_CALORIE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_KILO_CALORIE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_WATT_SECOND, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_KILO_WATT_SECOND, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_KILO_WATT_HOUR, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_WATT_HOUR, 0.0, catRowId));

    }

    private static void insertForce(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_FORCE));

        unitDao.insert(new Unit(UNIT_NEWTON, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_KILOGRAM_FORCE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_POUND_FORCE, 0.0, catRowId));

    }

    private static void insertHumidity(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_HUMIDITY));

        unitDao.insert(new Unit(UNIT_HUMIDITY_PERCENTAGE, 0.0, catRowId));

    }

    private static void insertLength(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_LENGTH));

        unitDao.insert(new Unit(UNIT_KILOMETER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_HECTOMETER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_METER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_DECIMETER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_CENTIMETER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MILLIMETER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MICROMETER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_NANOMETER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_ANGSTROM, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_PICO_METER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_FEMTO_METER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_FEMTO_METER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_INCHES, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_FEET, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_YARD, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_LIGHT_YEAR, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_PARSEC, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_PIXEL, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_POINT, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_PICA, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_EM, 0.0, catRowId));

    }

    private static void insertLuminance(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_LUMINANCE));

        unitDao.insert(new Unit(UNIT_CANDELA_SQUARE_METER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_CANDELA_SQUARE_CENTIMETER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_CANDELA_SQUARE_INCH, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_CANDELA_SQUARE_FOOT, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_LAMBERT, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_FOOT_LAMBERT, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_METER_LAMBERT, 0.0, catRowId));

    }

    private static void insertLuminousFlux(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_LUMINOUS_FLUX));

        unitDao.insert(new Unit(UNIT_LUX, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_PHOT, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_FOOT_CANDLE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_LUMEN_SQUARE_INCH, 0.0, catRowId));

    }

    private static void insertMass(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_MASS));

        unitDao.insert(new Unit(UNIT_GRAM, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_KILOGRAM, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MILLIGRAM, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_NANO_GRAM, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_PICO_GRAM, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_FEMTO_GRAM, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_OUNCE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_POUND, 0.0, catRowId));

    }

    private static void insertPressure(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_PRESSURE));

        unitDao.insert(new Unit(UNIT_PASCAL, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_KILO_PASCAL, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_HECTO_PASCAL, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MILLI_PASCAL, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_BAR, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_KILO_BAR, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_TOR, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_PSI, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_PSF, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_ATMOSPHERE, 0.0, catRowId));

    }

    private static void insertSpeed(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_SPEED));

        unitDao.insert(new Unit(UNIT_METER_PER_SECOND, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_KILO_METER_PER_HOUR, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MILES_PER_HOUR, 0.0, catRowId));

    }

    private static void insertTemperature(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_TEMPERATURE));

        unitDao.insert(new Unit(UNIT_KELVIN, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_CELSIUS, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_FAHRENHEIT, 0.0, catRowId));

    }

    private static void insertTemperatureGradient(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_TEMPERATURE_GRADIENT));

        unitDao.insert(new Unit(UNIT_KELVIN_PER_SECOND, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_KELVIN_PER_MINUTE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_KELVIN_PER_HOUR, 0.0, catRowId));

    }

    private static void insertTime(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_TIME));

        unitDao.insert(new Unit(UNIT_YEAR, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MONTH, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_WEEK, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_DAY, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_HOUR, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MINUTE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_SECOND, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MILLISECOND, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MICROSECOND, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_NANOSECOND, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_PICO_SECOND, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_FEMTO_SECOND, 0.0, catRowId));

    }

    private static void insertTorque(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_TORQUE));

        unitDao.insert(new Unit(UNIT_NEWTON_METER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_METER_KILOGRAM, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_FOOT_POUND_FORCE, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_INCH_POUND_FORCE, 0.0, catRowId));

    }

    private static void insertVolume(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_VOLUME));

        unitDao.insert(new Unit(UNIT_CUBIC_MILLIMETER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MILLILITER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_LITER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_CUBIC_METER, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_CUBIC_FEET, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_CUBIC_INCH, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_GALLON, 0.0, catRowId));

    }

    private static void insertVoltage(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_VOLTAGE));

        unitDao.insert(new Unit(UNIT_VOLT, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_KILO_VOLT, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MEGA_VOLT, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MILLI_VOLT, 0.0, catRowId));

    }

    private static void insertWork(CategoryDao categoryDao, UnitDao unitDao){
        long catRowId = categoryDao.insert(new Category(CATEGORY_WORK));

        unitDao.insert(new Unit(UNIT_WATT, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_KILO_WATT, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_MEGA_WATT, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_GIGA_WATT, 0.0, catRowId));
        unitDao.insert(new Unit(UNIT_HORSEPOWER, 0.0, catRowId));

    }


}
