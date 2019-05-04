package com.nimble.unitconvert.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.nimble.unitconvert.R;
import com.nimble.unitconvert.db.UnitConverterDatabase;
import com.nimble.unitconvert.model.Category;
import com.nimble.unitconvert.model.Unit;
import com.nimble.unitconvert.view.model.CategoryViewModel;
import com.nimble.unitconvert.view.model.UnitViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CategoryViewModel mCategoryViewModel;
    private UnitViewModel mUnitViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCategoryViewModel = ViewModelProviders.of(MainActivity.this).get(CategoryViewModel.class);
        mUnitViewModel = ViewModelProviders.of(MainActivity.this).get(UnitViewModel.class);

        mCategoryViewModel.getAll()
                .observe(MainActivity.this, new Observer<List<Category>>() {
                    @Override
                    public void onChanged(List<Category> categories) {
                        //Update adapter
                    }
                });

        mUnitViewModel.getAll()
                .observe(MainActivity.this, new Observer<List<Unit>>() {
                    @Override
                    public void onChanged(List<Unit> unitList) {
                        //Update adapter
                    }
                });

    }
}
