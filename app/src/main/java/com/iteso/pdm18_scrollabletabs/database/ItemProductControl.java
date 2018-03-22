package com.iteso.pdm18_scrollabletabs.database;

import android.content.ClipData;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;

import java.util.ArrayList;

/**
 * Created by Viral on 19/3/2018.
 */

public class ItemProductControl {

    public long addItemProduct(ItemProduct product, DataBaseHandler dh){
        long inserted = 0;
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_PRODUCT_ID, product.getCode());
        values.put(DataBaseHandler.KEY_PRODUCT_TITLE, product.getTitle());
        values.put(DataBaseHandler.KEY_PRODUCT_IMAGE, product.getImage());



        inserted = db.insert(DataBaseHandler.TABLE_PRODUCT,null, values);
        //inserted = db.insert(DataBaseHandler.TABLE_STORE,null, values);

        try {db.close();}  catch (Exception e){}
        db = null;
        values = null;
        return inserted;

    }

    public ArrayList<ItemProduct> getProductsWhere( DataBaseHandler dh){

        ItemProduct itemProduct = new ItemProduct();
        String selectQuery = "Select S." + dh.KEY_PRODUCT_ID + ","
                + "S." + dh.KEY_PRODUCT_TITLE + ","
                + "S." + dh.KEY_PRODUCT_CATEGORY + ","
                + "S." + dh.KEY_PRODUCT_IMAGE +
                "FROM "
                + dh.TABLE_PRODUCT ;
            SQLiteDatabase db = dh.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if(cursor.moveToFirst()){
                itemProduct.setCode(cursor.getInt(0));
                itemProduct.setTitle(cursor.getString(1));
                itemProduct.setImage(cursor.getInt(2));


            }

        try {db.close();}  catch (Exception e){}
        db = null;
        cursor = null;

            return itemProduct;


    }

}
