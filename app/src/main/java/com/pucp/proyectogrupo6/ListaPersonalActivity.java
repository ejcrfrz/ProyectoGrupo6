package com.pucp.proyectogrupo6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.pucp.proyectogrupo6.Entidades.Incidencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListaPersonalActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String Name_DB="CUACK";
    TextView editText_name;
    private ArrayList<Incidencia> incidencias = new ArrayList<>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personal);

        //---------------------------------------------------------------------
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view1);
        toolbar = findViewById(R.id.toolbar1);
        mAuth = FirebaseAuth.getInstance();
        setSupportActionBar(toolbar);
        final String id = mAuth.getCurrentUser().getUid();
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_crear:


                        Intent i = new Intent(ListaPersonalActivity.this,RegisterIncidentActivity.class);
                        i.putExtra("id", id);
                        startActivity(i);
                        break;
                    case R.id.nav_lista:
                        break;
                }
                return true;
            }
        });
        //--------------------------------------------------------

        mDatabase = FirebaseDatabase.getInstance().getReference();
        editText_name = findViewById(R.id.Nombre);
        getUserinfo();
        publicationValueEventListener();


    }




    //Habilitar Internet
    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) return false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network networks = connectivityManager.getActiveNetwork();
            if (networks == null) return false;

            NetworkCapabilities networkCapabilities =
                    connectivityManager.getNetworkCapabilities(networks);
            if (networkCapabilities == null) return false;

            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                return true;
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                return true;
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
                return true;
            return false;
        } else {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) return false;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) return true;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) return true;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_ETHERNET) return true;
            return false;
        }

    }

    public void CerraSesion(View view){

        mAuth.signOut();
        startActivity(new Intent(ListaPersonalActivity.this, ActivityLogin.class));
        finish();


    }

    private void getUserinfo() {
        String id = mAuth.getCurrentUser().getUid();
        Log.d("TAG1", id);


        mDatabase.child("usuarios/"+id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()) {
                    Name_DB = dataSnapshot.child("nombre").getValue().toString();
                    Log.d("TAG2", Name_DB);
                    editText_name.setText(dataSnapshot.child("nombre").getValue().toString());
                    // Email_DB = dataSnapshot.child("email").getValue().toString();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


    public void publicationValueEventListener() {
        mDatabase.child("incidentes").addValueEventListener(new ValueEventListener() { // CAMBIAR POR UN userId VARIABLE
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) { // cada vez que hay un cambio en Firebase
                Log.d("dataSnapshotJson", dataSnapshot.getValue().toString()); // dataSnapshot contiene el json (equivalente a gson.fromJson)

                incidencias.clear(); // vaciar el ArrayList de publicaciones, para llenar la lista nuevamente
                // Iterar por todas las publicaciones del JSON
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Incidencia inc1 = postSnapshot.getValue(Incidencia.class);
                    incidencias.add(inc1);  // agregar todas las publicaciones a un arreglo
                }

                buildPublicationRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { // si hay un error al obtener la información en Firebase

            }
        });

    }
    // Método para construir el RecyclerView
    public void buildPublicationRecyclerView(){


        ListaIncidenciasPersonalAdapter listaIncidenciasPersonalAdapter = new ListaIncidenciasPersonalAdapter(incidencias,ListaPersonalActivity.this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(listaIncidenciasPersonalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListaPersonalActivity.this));

    }








}
