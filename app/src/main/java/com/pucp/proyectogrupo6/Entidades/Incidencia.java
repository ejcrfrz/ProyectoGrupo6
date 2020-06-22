package com.pucp.proyectogrupo6.Entidades;

public class Incidencia {

    private int idAccidente;
    private  String nombreAccidente;
    private String descripcion;
    private String ubicacion;
    private String foto;
    private int idUsuario;
    private String estado;
    private String comentario;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }



    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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


    public int getIdAccidente() {
        return idAccidente;
    }

    public void setIdAccidente(int idAccidente) {
        this.idAccidente = idAccidente;
    }

    public String getNombreAccidente() {
        return nombreAccidente;
    }

    public void setNombreAccidente(String nombreAccidente) {
        this.nombreAccidente = nombreAccidente;
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
