package com.nimble.unitconvert.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.nimble.unitconvert.dao.UnitDao;
import com.nimble.unitconvert.db.UnitConverterDatabase;
import com.nimble.unitconvert.model.Unit;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UnitRepository implements UnitDao {

    private UnitDao unitDao;
    //private LiveData<List<Unit>> allUnits;

    public UnitRepository(Application application/*, long catId*/){
        UnitConverterDatabase database = UnitConverterDatabase.getInstance(application);
        unitDao = database.unitDao();
        //allUnits = unitDao.getAllUnits(catId);
    }

    @Override
    public long insert(Unit unit) {
        long lastInsertedUnit = 0;

        try {
            lastInsertedUnit = new InsertUnitAsyncTask(unitDao).execute(unit).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return lastInsertedUnit;

    }

    @Override
    public Unit getUnitById(long unitId) {
        Unit unit = null;

        try {
            unit = new SelectUnitAsyncTask(unitDao).execute(unitId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return unit;
    }

    @Override
    public void update(Unit unit) {
        new UpdateUnitAsyncTask(unitDao).execute(unit);
    }

    @Override
    public void delete(Unit unit) {
        new DeleteUnitAsyncTask(unitDao).execute(unit);
    }

    @Override
    public void deleteAllUnits() {
        new DeleteAllUnitsAsyncTask(unitDao).execute();
    }

    @Override
    public LiveData<List<Unit>> getAllUnits(long catId) {
        LiveData<List<Unit>> allUnitsByCatId = null;
        try {
            allUnitsByCatId = new SelectUnitListByCategoryId(unitDao).execute(catId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return allUnitsByCatId;
    }

    @Override
    public double getUnitRateByUnitName(String unitName) {
        Double unitRate = 0.0;
        try {
            unitRate = new SelectUnitRateAsyncTask(unitDao).execute(unitName).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return unitRate;
    }

    private static class SelectUnitListByCategoryId extends AsyncTask<Long, Void, LiveData<List<Unit>>>{

        private UnitDao unitDao;

        private SelectUnitListByCategoryId(UnitDao unitDao){
            this.unitDao = unitDao;
        }

        @Override
        protected LiveData<List<Unit>> doInBackground(Long... longs) {
            return unitDao.getAllUnits(longs[0]);
        }
    }

    private static class SelectUnitRateAsyncTask extends AsyncTask<String, Void, Double>{

        private UnitDao unitDao;

        private SelectUnitRateAsyncTask(UnitDao unitDao){
            this.unitDao = unitDao;
        }

        @Override
        protected Double doInBackground(String... strings) {
            return unitDao.getUnitRateByUnitName(strings[0]);
        }
    }

    private static class SelectUnitAsyncTask extends AsyncTask<Long, Void, Unit>{

        private UnitDao unitDao;

        private SelectUnitAsyncTask(UnitDao unitDao){
            this.unitDao = unitDao;
        }

        @Override
        protected Unit doInBackground(Long... longs) {
            return unitDao.getUnitById(longs[0]);
        }
    }

    private static class InsertUnitAsyncTask extends AsyncTask<Unit, Void, Long>{

        private UnitDao unitDao;

        private InsertUnitAsyncTask(UnitDao unitDao){
            this.unitDao = unitDao;
        }

        @Override
        protected Long doInBackground(Unit... units) {
            return unitDao.insert(units[0]);
        }
    }

    private static class UpdateUnitAsyncTask extends AsyncTask<Unit, Void, Void>{

        private UnitDao unitDao;

        private UpdateUnitAsyncTask(UnitDao unitDao){
            this.unitDao = unitDao;
        }

        @Override
        protected Void doInBackground(Unit... units) {
            unitDao.update(units[0]);
            return null;
        }
    }

    private static class DeleteUnitAsyncTask extends AsyncTask<Unit, Void, Void>{

        private UnitDao unitDao;

        private DeleteUnitAsyncTask(UnitDao unitDao){
            this.unitDao = unitDao;
        }

        @Override
        protected Void doInBackground(Unit... units) {
            unitDao.delete(units[0]);
            return null;
        }
    }

    private static class DeleteAllUnitsAsyncTask extends AsyncTask<Void, Void, Void>{

        private UnitDao unitDao;

        private DeleteAllUnitsAsyncTask(UnitDao unitDao){
            this.unitDao = unitDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            unitDao.deleteAllUnits();
            return null;
        }
    }

}
