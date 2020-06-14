package com.pucp.proyectogrupo6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class VerDetalleActivity extends AppCompatActivity {

    String nombre_incidencia = "";
    String estado = "";
    String descripcion = "";
    String textViewMaxSalary = "";
    String foto = "";
    String ubicacion = "";
    String comentario = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_detalle);

        //Colocando los resultados
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nombre_incidencia = extras.getString("nombre_incidencia");
            estado = extras.getString("estado");
            descripcion = extras.getString("descripcion");
            foto = extras.getString("foto");
            ubicacion = extras.getString("ubicacion");
            comentario = extras.getString("comentario");

        }

        TextView textViewverNombre = findViewById(R.id.verNombre);
        TextView textViewverDescripcion = findViewById(R.id.verDescripcion);
        TextView textViewverUbicacion = findViewById(R.id.verUbicacion);
        TextView textViewverFoto = findViewById(R.id.verFoto);
        TextView textViewverEstado = findViewById(R.id.verEstado);
        TextView textViewverComentario = findViewById(R.id.verComentario);


        textViewverNombre.setText(nombre_incidencia);
        textViewverDescripcion.setText(descripcion);
        textViewverUbicacion.setText(ubicacion);
        textViewverFoto.setText(foto);
        textViewverEstado.setText(estado);
        textViewverComentario.setText(comentario);

    }





}
