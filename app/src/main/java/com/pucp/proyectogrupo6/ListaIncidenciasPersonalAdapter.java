package com.pucp.proyectogrupo6;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pucp.proyectogrupo6.Entidades.Incidencia;

import java.util.List;

public class ListaIncidenciasPersonalAdapter extends RecyclerView.Adapter<ListaIncidenciasPersonalAdapter.IncidenciaPersonalViewHolder> {


    List<Incidencia> listaincidente;
    Context contexto;

    public ListaIncidenciasPersonalAdapter(List<Incidencia> lista, Context c) {

        this.listaincidente = lista;
        this.contexto = c;
    }


    public static class IncidenciaPersonalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombre_incidencia;
        TextView estado;
        String descripcion;
        String ubicacion;
        String foto;
        String comentario;
        String idAccidente;
        //buttons
        Button buttonVerDetalle;
        Button buttonHecho;

        Context context;

        public IncidenciaPersonalViewHolder(View itemView) {
            super(itemView);

            // inicializar contexto
            context = itemView.getContext();
            nombre_incidencia = itemView.findViewById(R.id.nombre_incidencia);
            estado = itemView.findViewById(R.id.estado);

            //Referencia a botones:
            buttonVerDetalle = itemView.findViewById(R.id.buttonDetalle);
            buttonHecho = itemView.findViewById(R.id.buttonHecho);

        }

        void setOnClickListeners() {

            buttonVerDetalle.setOnClickListener(this);
            buttonHecho.setOnClickListener(this);

        }

        //---------------------------------------------------------------------------

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.buttonDetalle:

                    Intent intent = new Intent(context, VerDetalleActivity.class);
                    intent.putExtra("nombre_incidencia", nombre_incidencia.getText());
                    intent.putExtra("estado", estado.getText());
                    intent.putExtra("descripcion", descripcion);
                    intent.putExtra("foto", foto);
                    intent.putExtra("ubicacion", ubicacion);
                    intent.putExtra("comentario", comentario);

                    //intent.putExtra("listatrabajos",listTrabajos);
                    context.startActivity(intent);
                    break;

                case R.id.buttonHecho:
                    Intent intent1 = new Intent(context, AgregarComentarioActivity.class);
                    intent1.putExtra("idAccidente", idAccidente);
                    context.startActivity(intent1);
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
        Incidencia incidencia = listaincidente.get(position);
        String getnomb_accidente = incidencia.getNombre_accidente();
        String getestado = incidencia.getEstado();
        String getdescripcion = incidencia.getDescripcion();
        String getfoto = incidencia.getFoto();
        String getubicacion = incidencia.getUbicacion();
        String getidaccidente = String.valueOf(incidencia.getIdaccidentes());
        String getcomentario = incidencia.getComentario();
        holder.nombre_incidencia.setText(getnomb_accidente);
        holder.estado.setText(getestado);
        holder.descripcion = getdescripcion;
        holder.foto = getfoto;
        holder.ubicacion = getubicacion;
        holder.idAccidente = getidaccidente;
        if(!getcomentario.equals("")){
                        holder.buttonHecho.setVisibility(View.INVISIBLE);
        }

        holder.comentario = getcomentario;
        holder.setOnClickListeners();

    }

    @Override
    public int getItemCount() {

        return listaincidente.size();
    }


}
