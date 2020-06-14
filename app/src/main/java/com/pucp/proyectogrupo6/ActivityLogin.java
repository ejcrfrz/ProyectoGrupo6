package com.pucp.proyectogrupo6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
/*
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
*/

    }
}
