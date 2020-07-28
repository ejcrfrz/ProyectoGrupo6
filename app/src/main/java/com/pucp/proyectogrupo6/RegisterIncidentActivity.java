package com.pucp.proyectogrupo6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.pucp.proyectogrupo6.Entidades.Incidencia;

public class RegisterIncidentActivity extends AppCompatActivity {

    String latitud;
    String altitud;
    ImageView imagen;
    Uri path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_incident);
        imagen = findViewById(R.id.imageViewFotoIncidencia);

    }

    private void mostrarInfoDeUbicacion() {
        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permiso == PackageManager.PERMISSION_GRANTED){
            FusedLocationProviderClient fusedLocationProviderClient =
                    LocationServices.getFusedLocationProviderClient(this);

            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null){
                        TextView textView  = findViewById(R.id.textViewLocalizacion);
                        String ubicacion = "Latitud: " + location.getLatitude() + "   Longitud: "
                                + location.getAltitude();
                        textView.setText(ubicacion);
                    }
                }
            });

            fusedLocationProviderClient.getLastLocation().addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        }else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }

    }


    public void botonUbicacion(View view){
        mostrarInfoDeUbicacion();
    }

    public void cargarImagen(View view){

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la aplicacion"),10);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            path =  data.getData();
            imagen.setImageURI(path);
        }


    }


    public void guardarIncidencia(View view){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Incidencias");

        Incidencia incidencia = new Incidencia();
        String nombreIndicencia = findViewById(R.id.editTextNombreIncidencia).toString();
        String descripcion = findViewById( R.id.editTextDescripcionIncidencia).toString();
        String ubicacion = findViewById(R.id.textViewLocalizacion).toString();
        DatabaseReference dbPush = databaseReference.push();
        String idIncidencia = dbPush.getKey();

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        String fileName = "img-"+idIncidencia;
        StorageReference storageRef = firebaseStorage.getReference().child("Img-Incidencias/" + fileName);
        UploadTask uploadTask = storageRef.putFile(path);


        incidencia.setNombre_accidente(nombreIndicencia);
        incidencia.setDescripcion(descripcion);
        incidencia.setUbicacion(ubicacion);
        incidencia.setFoto(path.toString());
        incidencia.setIdaccidentes(idIncidencia);

        dbPush.setValue(incidencia).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Guardado exitoso",Toast.LENGTH_SHORT);
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Upss, Hubo problemas",Toast.LENGTH_SHORT);
            }
        });

    }
}
