package com.nimble.unitconvert.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.nimble.unitconvert.R;
import com.nimble.unitconvert.model.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends BaseAdapter {

    private Context context;
    List<Category> categories;
    //int categoryIcon[];

    AppCompatImageView catIcon;

    AppCompatTextView catName;

    public CategoryAdapter(Context context, List<Category> categories/*, int[] categoryIcon*/) {
        this.context = context;
        this.categories = categories;
        //this.categoryIcon = categoryIcon;

    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder")
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, null);
        //ButterKnife.bind(context, view);
        catName = view.findViewById(R.id.tv_cat_name);
        //catIcon.setImageResource(categoryIcon[position]);
        Typeface tf = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "fonts/eau_sans_book.ttf");
        catName.setTypeface(tf);
        catName.setText(categories.get(position).getCategoryName());
        return view;
    }
}
