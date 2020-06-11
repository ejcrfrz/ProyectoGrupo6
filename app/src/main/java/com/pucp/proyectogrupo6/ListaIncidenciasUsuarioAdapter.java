package com.pucp.proyectogrupo6;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.pucp.proyectogrupo6.Entidades.Incidencia;

import org.w3c.dom.Text;

public class ListaIncidenciasUsuarioAdapter extends RecyclerView.Adapter<ListaIncidenciasUsuarioAdapter.IncidenciaUsuarioViewHolder> {

    Incidencia[] listaincidente;
    Context contexto;

    public ListaIncidenciasUsuarioAdapter(Incidencia[] lista, Context c) {

        this.listaincidente = lista;
        this.contexto = c;
    }




    public static class IncidenciaUsuarioViewHolder extends RecyclerView.ViewHolder{
        TextView  id_incidencia;
        Context context;
        public IncidenciaUsuarioViewHolder(View itemView){
            super(itemView);
            context = itemView.getContext();
            id_incidencia = itemView.findViewById(R.id.Id_incidencia);

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
    holder.id_incidencia.setText(texto);


    }

    @Override
    public int getItemCount() {

        return listaincidente.length;
    }




}
