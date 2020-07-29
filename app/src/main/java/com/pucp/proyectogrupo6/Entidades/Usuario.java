package com.pucp.proyectogrupo6.Entidades;

import java.io.Serializable;
import java.time.LocalDate;

public class Usuario implements Serializable {
    private int idusuario;
    private String correo;

    private String nombre;
    private int permiso;
    private int estadoCuenta;
    private String contrasena;
    private int infraestructura = 0;

    public int getInfraestructura() {
        return infraestructura;
    }

    public void setInfraestructura(int infraestructura) {
        this.infraestructura = infraestructura;
    }
    public int getPermiso() {
        return permiso;
    }

    public void setPermiso(int permiso) {
        this.permiso = permiso;
    }





    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }




    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public int getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(int estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }



}
