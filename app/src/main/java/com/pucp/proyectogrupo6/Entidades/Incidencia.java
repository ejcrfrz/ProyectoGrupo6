package com.pucp.proyectogrupo6.Entidades;

public class Incidencia {

    private int idaccidentes;
    private  String nombre_accidente;
    private String descripcion;
    private String ubicacion;
    private String foto;
    private int idusuarios;
    private String estado;


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public int getIdaccidentes() {
        return idaccidentes;
    }

    public void setIdaccidentes(int idaccidentes) {
        this.idaccidentes = idaccidentes;
    }

    public String getNombre_accidente() {
        return nombre_accidente;
    }

    public void setNombre_accidente(String nombre_accidente) {
        this.nombre_accidente = nombre_accidente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getIdusuarios() {
        return idusuarios;
    }

    public void setIdusuarios(int idusuarios) {
        this.idusuarios = idusuarios;
    }



}
