package com.nimble.unitconvert.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.nimble.unitconvert.R;
import com.nimble.unitconvert.db.UnitConverterDatabase;
import com.nimble.unitconvert.model.Category;
import com.nimble.unitconvert.model.Unit;
import com.nimble.unitconvert.view.adapter.CategoryAdapter;
import com.nimble.unitconvert.view.model.CategoryViewModel;
import com.nimble.unitconvert.view.model.UnitViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private CategoryViewModel mCategoryViewModel;
    private UnitViewModel mUnitViewModel;

    @BindView(R.id.spn_category)
    AppCompatSpinner mCategorySpinner;

    //int categoryIcon[] = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(MainActivity.this);

        mCategoryViewModel = ViewModelProviders.of(MainActivity.this).get(CategoryViewModel.class);
        mUnitViewModel = ViewModelProviders.of(MainActivity.this).get(UnitViewModel.class);

        mCategoryViewModel.getAll()
                .observe(MainActivity.this, new Observer<List<Category>>() {
                    @Override
                    public void onChanged(List<Category> categories) {
                        //Set Category Adapter
                        CategoryAdapter categoryAdapter = new CategoryAdapter(MainActivity.this, categories);
                        mCategorySpinner.setAdapter(categoryAdapter);

                        for (Category category : categories){
                            Log.e("MAIN", "Category Name is : " + category.getCategoryName());



                            // call all units with catId
                            mUnitViewModel.getAll(category.getId())
                                    .observe(MainActivity.this, new Observer<List<Unit>>() {
                                        @Override
                                        public void onChanged(List<Unit> unitList) {
                                            //Update adapter
                                            for (Unit unit : unitList){
                                                Log.e("MAIN", "Unit Name is : " + unit.getUnitName() + " and Cate_id is : " + unit.getCategoryId());
                                            }
                                        }
                                    });

                        }
                    }
                });

        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/eau_sans_book.ttf");
        //textView.setTypeface(tf);

    }
}
