package com.iteso.pdm18_scrollabletabs.tools;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iteso.pdm18_scrollabletabs.R;
import com.iteso.pdm18_scrollabletabs.beans.User;

public class ActivityLogin extends AppCompatActivity {

    EditText name, psw;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.username);
        psw = findViewById(R.id.password);
        login = findViewById(R.id.activity_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreferences();
                Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void savePreferences(){
        User user = new User();
        user.setName(name.getText().toString());
        user.setPsw(psw.getText().toString());
        user.setLogged(true);

        SharedPreferences sharedPreferences = getSharedPreferences(
                "com.iteso.USER_PREFERENCES", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name",user.getName());
        editor.putString("PWD", user.getPsw());
        editor.putBoolean("LOGIN", user.isLogged());
        editor.apply();
    }

}
