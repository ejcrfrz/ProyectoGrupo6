package com.pucp.proyectogrupo6;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pucp.proyectogrupo6.Entidades.Incidencia;

public class ListaIncidenciasPersonalAdapter extends RecyclerView.Adapter<ListaIncidenciasPersonalAdapter.IncidenciaPersonalViewHolder> {

    Incidencia[] listaincidente;
    Context contexto;

    public ListaIncidenciasPersonalAdapter(Incidencia[] lista, Context c) {

        this.listaincidente = lista;
        this.contexto = c;
    }


    public static class IncidenciaPersonalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombre_incidencia;
        TextView estado;
        TextView descripcion;
        TextView ubicacion;
        TextView foto;
        //buttons
        Button buttonVerDetalle;

        Context context;

        public IncidenciaPersonalViewHolder(View itemView) {
            super(itemView);

            // inicializar contexto
            context = itemView.getContext();
            nombre_incidencia = itemView.findViewById(R.id.nombre_incidencia);
            estado = itemView.findViewById(R.id.estado);
            descripcion = itemView.findViewById(R.id.descripcion);
            foto = itemView.findViewById(R.id.foto);
            ubicacion = itemView.findViewById(R.id.ubicacion);
            //Referencia a botones:
            buttonVerDetalle = itemView.findViewById(R.id.buttonDetalle);
        }

        void setOnClickListeners() {

            buttonVerDetalle.setOnClickListener(this);


        }

        //---------------------------------------------------------------------------

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.buttonDetalle:

                    Intent intent = new Intent(context, VerDetalleActivity.class);
                    intent.putExtra("nombre_incidencia", nombre_incidencia.getText());
                    intent.putExtra("estado", estado.getText());
                    intent.putExtra("descripcion", descripcion.getText());
                    intent.putExtra("foto", foto.getText());
                    intent.putExtra("ubicacion", ubicacion.getText());

                    //intent.putExtra("listatrabajos",listTrabajos);
                    context.startActivity(intent);
                    break;

            }
        }
        //--------------------------------------------------------------------------

    }


    @NonNull
    @Override
    public IncidenciaPersonalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(contexto).inflate(R.layout.rv_personal, parent, false);
        IncidenciaPersonalViewHolder incidenciaPersonalViewHolder = new IncidenciaPersonalViewHolder(item);
        return incidenciaPersonalViewHolder;
    }

    @Override
    public void onBindViewHolder(IncidenciaPersonalViewHolder holder, int position) {
        Incidencia incidencia = listaincidente[position];
        String getnomb_accidente = incidencia.getNombre_accidente();
        String getestado = incidencia.getEstado();
        String getdescripcion = incidencia.getDescripcion();
        String getfoto = incidencia.getFoto();
        String getubicacion = incidencia.getUbicacion();

        holder.nombre_incidencia.setText(getnomb_accidente);
        holder.estado.setText(getestado);
        holder.descripcion.setText(getdescripcion);
        holder.foto.setText(getfoto);
        holder.ubicacion.setText(getubicacion);

        holder.setOnClickListeners();

    }

    @Override
    public int getItemCount() {

        return listaincidente.length;
    }


}
