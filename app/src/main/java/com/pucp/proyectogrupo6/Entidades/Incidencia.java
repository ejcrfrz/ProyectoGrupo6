package com.pucp.proyectogrupo6.Entidades;

public class Incidencia {

    private String idaccidentes;
    private String nombre_accidente;
    private String descripcion;
    private String ubicacion;
    private String idusuario;
    private String estado;
    private String comentario;
    private String foto;




    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }



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



    public String getNombre_accidente() {
        return nombre_accidente;
    }

    public void setNombre_accidente(String nombre_accidente) {
        this.nombre_accidente = nombre_accidente;
    }


    public String getIdaccidentes() {
        return idaccidentes;
    }

    public void setIdaccidentes(String idaccidentes) {
        this.idaccidentes = idaccidentes;
    }

}
