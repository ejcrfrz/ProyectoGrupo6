package com.pucp.proyectogrupo6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pucp.proyectogrupo6.Entidades.Cursos;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        Cursos cursos=new Cursos();
        cursos.setApellido("Lopezc");
        cursos.setNombre("campos");

        databaseReference.setValue(cursos).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("infoApp","guardado exitosamente");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("infoapp","error");
            }
        });
    }
    public void ingresarOnClick(View view) {
        Intent i = new Intent(ActivityLogin.this,ListaUsuarioActivity.class);
        startActivity(i);

    }




}
