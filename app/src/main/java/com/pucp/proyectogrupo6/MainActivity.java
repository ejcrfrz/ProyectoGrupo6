package com.pucp.proyectogrupo6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void asd(View view) {
        Intent i = new Intent(MainActivity.this,ActivityLogin.class);
        startActivity(i);

    }


    public void ListaPersonal(View view) {
        Intent i = new Intent(MainActivity.this,ListaPersonalActivity.class);
        startActivity(i);

    }

}
