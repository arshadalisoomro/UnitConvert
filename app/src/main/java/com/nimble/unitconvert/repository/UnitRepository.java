package com.nimble.unitconvert.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.nimble.unitconvert.dao.UnitDao;
import com.nimble.unitconvert.db.UnitDatabase;
import com.nimble.unitconvert.model.Unit;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UnitRepository implements UnitDao {

    private UnitDao unitDao;
    private LiveData<List<Unit>> allUnits;

    public UnitRepository(Application application){
        UnitDatabase database = UnitDatabase.getInstance(application);
        unitDao = database.unitDao();
        allUnits = unitDao.getAllUnits();
    }

    @Override
    public void insertUnit(Unit unit) {
        new InsertUnitAsyncTask(unitDao).execute(unit);
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
    public void updateUnit(Unit unit) {
        new UpdateUnitAsyncTask(unitDao).execute(unit);
    }

    @Override
    public void deleteUnit(Unit unit) {
        new DeleteUnitAsyncTask(unitDao).execute(unit);
    }

    @Override
    public void deleteAllUnits() {
        new DeleteAllUnitsAsyncTask(unitDao).execute();
    }

    @Override
    public LiveData<List<Unit>> getAllUnits() {
        return allUnits;
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

    private static class InsertUnitAsyncTask extends AsyncTask<Unit, Void, Void>{

        private UnitDao unitDao;

        private InsertUnitAsyncTask(UnitDao unitDao){
            this.unitDao = unitDao;
        }

        @Override
        protected Void doInBackground(Unit... units) {
            unitDao.insertUnit(units[0]);
            return null;
        }
    }

    private static class UpdateUnitAsyncTask extends AsyncTask<Unit, Void, Void>{

        private UnitDao unitDao;

        private UpdateUnitAsyncTask(UnitDao unitDao){
            this.unitDao = unitDao;
        }

        @Override
        protected Void doInBackground(Unit... units) {
            unitDao.updateUnit(units[0]);
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
            unitDao.deleteUnit(units[0]);
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
