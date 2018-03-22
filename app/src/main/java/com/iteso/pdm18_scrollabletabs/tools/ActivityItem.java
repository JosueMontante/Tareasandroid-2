package com.iteso.pdm18_scrollabletabs.tools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.iteso.pdm18_scrollabletabs.R;
import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.Store;
import com.iteso.pdm18_scrollabletabs.database.DataBaseHandler;
import com.iteso.pdm18_scrollabletabs.database.StoreControl;

/**
 * Created by Viraloch on 18/3/2018.
 */

public class ActivityItem extends AppCompatActivity {

    protected EditText titulo;
    protected Spinner imagen, categorias, tiendas;
    protected Button save;
    protected ArrayAdapter<Store> storeAdapter;
    protected ArrayAdapter<Category> categoryAdapter;
    protected ArrayAdapter<String> imageAdapter;
    protected DataBaseHandler dh;
    protected Store storeSelected;
    protected Category categorySelected;
    protected int imageSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        titulo = findViewById(R.id.activity_item_titulo);
        imagen = findViewById(R.id.activity_item_spinner_image);
        categorias = findViewById(R.id.activity_item_spinner_categorias);
        tiendas = findViewById(R.id.activity_item_spinner_tiendas);
        save = findViewById(R.id.activity_product_save);

        storeSelected = null;
        categorySelected = null;
        imageSelected = -1;

        dh = DataBaseHandler.getInstance(this);
        StoreControl storeControl = new StoreControl();
        CategoryControl categoryControl = new CategoryControl();
    }
}
