package com.iteso.pdm18_scrollabletabs.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.beans.Category;

import static com.iteso.pdm18_scrollabletabs.database.DataBaseHandler.KEY_CATEGORY_ID;
import static com.iteso.pdm18_scrollabletabs.database.DataBaseHandler.TABLE_CATEGORY;

/**
 * Created by Viraloch on 20/3/2018.
 */

public class CategoryControl {

    public String getCategories (DataBaseHandler dh){



        Category cat = new Category();
        String selectQuery = ("SELECT "+ KEY_CATEGORY_ID +"FROM "+ TABLE_CATEGORY,null);
        SQLiteDatabase db= dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        return selectQuery;



    }
}
