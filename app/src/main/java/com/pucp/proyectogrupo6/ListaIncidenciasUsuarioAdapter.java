package com.pucp.proyectogrupo6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pucp.proyectogrupo6.Entidades.Incidencia;

public class ListaIncidenciasUsuarioAdapter extends RecyclerView.Adapter<ListaIncidenciasUsuarioAdapter.IncidenciaUsuarioViewHolder> {

    Incidencia[] listaincidente;
    Context contexto;

    public ListaIncidenciasUsuarioAdapter(Incidencia[] lista, Context c) {

        this.listaincidente = lista;
        this.contexto = c;
    }




    public static class IncidenciaUsuarioViewHolder extends RecyclerView.ViewHolder{
        TextView  nombre_incidencia;
        TextView  estado;

        Context context;
        public IncidenciaUsuarioViewHolder(View itemView){
            super(itemView);
            context = itemView.getContext();
            nombre_incidencia = itemView.findViewById(R.id.nombre_incidencia);
            estado = itemView.findViewById(R.id.estado);

        }

    }
    @NonNull
    @Override
    public IncidenciaUsuarioViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(contexto).inflate(R.layout.rv_usuario,parent,false);
        IncidenciaUsuarioViewHolder incidenciaUsuarioViewHolder = new IncidenciaUsuarioViewHolder(item);
        return incidenciaUsuarioViewHolder;
    }

    @Override
    public void onBindViewHolder( IncidenciaUsuarioViewHolder holder, int position) {
    Incidencia incidencia = listaincidente[position];
    String texto  = incidencia.getNombre_accidente();
    String texto1 = incidencia.getEstado();
    holder.nombre_incidencia.setText(texto);
    holder.estado.setText(texto1);

    }

    @Override
    public int getItemCount() {

        return listaincidente.length;
    }




}
