package com.iteso.pdm18_scrollabletabs.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.iteso.pdm18_scrollabletabs.beans.City;
import com.iteso.pdm18_scrollabletabs.beans.Store;

import java.util.ArrayList;

import static com.iteso.pdm18_scrollabletabs.database.DataBaseHandler.KEY_STORE_NAME;
import static com.iteso.pdm18_scrollabletabs.database.DataBaseHandler.TABLE_CATEGORY;

/**
 * Created by Viraloch on 18/3/2018.
 */

public class StoreControl {

    public ArrayList<Store> getStores(DataBaseHandler dh){
        ArrayList<Store> result = new ArrayList<Store>();
        SQLiteDatabase db= dh.getReadableDatabase();

        try {
            Cursor c=null;
            c= db.rawQuery("SELECT "+ KEY_STORE_NAME + "FROM " +TABLE_CATEGORY+ " WHERE "+ KEY_STORE_NAME +" IS NOT NULL",null);

                for (c.moveToFirst();  !c.isAfterLast(); c.moveToNext()){
                    Store pt = c
                    result.add(c);
                }

        }catch (Exception e){

        }

        return result;
        db=null;
    }

    public void addStore (Store store , DataBaseHandler dh){
        long inserted = 0;
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_STORE_CITY, store.getCity().getIdCity());
        values.put(DataBaseHandler.KEY_STORE_LAT, store.getLatitude());
        values.put(DataBaseHandler.KEY_STORE_LNG, store.getLongitude());
        values.put(DataBaseHandler.KEY_STORE_NAME, store.getName());
        values.put(DataBaseHandler.KEY_STORE_PHONE, store.getPhone());
        values.put(DataBaseHandler.KEY_STORE_THUMBNAIL, store.getThumbnail());

        //Inserting rows
        inserted = db.insert(DataBaseHandler.TABLE_STORE, null, values);
        //Closing database con
        try {db.close();}  catch (Exception e){}
        db = null;
        values = null;
    }

    public void deleteStore(int idStore, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete(DataBaseHandler.TABLE_STORE, DataBaseHandler.KEY_STORE_ID + " = ?",
                 new String[] {String.valueOf(idStore)});
        try {db.close();}  catch (Exception e){}
        db = null;
    }

    public Store getStoreById(int idStore ,DataBaseHandler dh){

        Store store = new Store();
        String selectQuery = "SELECT S." + DataBaseHandler.KEY_STORE_ID + ","
                + "S." + DataBaseHandler.KEY_STORE_LAT + ","
                + "S." + DataBaseHandler.KEY_STORE_LNG + ","
                + "S." + DataBaseHandler.KEY_STORE_NAME + ","
                + "S." + DataBaseHandler.KEY_STORE_PHONE + ","
                + "S." + DataBaseHandler.KEY_STORE_THUMBNAIL + ","
                + "C." + DataBaseHandler.KEY_CITY_ID + ","
                + "C." + DataBaseHandler.KEY_CITY_NAME + " FROM"
                + DataBaseHandler.TABLE_STORE + "S, "
                + DataBaseHandler.TABLE_CITY + "C WHERE S."
                + DataBaseHandler.KEY_STORE_ID + "= " + idStore
                + " AND S." + DataBaseHandler.KEY_STORE_CITY
                + " = C." + DataBaseHandler.KEY_CITY_ID;

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            City city = new City();
            store.setId(cursor.getInt(0));
            store.setLatitude(cursor.getDouble(1));
            store.setLongitude(cursor.getDouble(2));
            store.setName(cursor.getString(3));
            store.setPhone(cursor.getString(4));
            store.setThumbnail(cursor.getInt(5));
            city.setIdCity(cursor.getInt(6));
            city.setName(cursor.getString(7));
            store.setCity(city);
        }
        try {db.close();}  catch (Exception e){}
        db = null;
        cursor = null;
        return store;
    }

}
