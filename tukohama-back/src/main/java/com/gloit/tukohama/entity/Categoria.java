package com.gloit.tukohama.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String color;

    private String icono;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    private String creador;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    private String modificador;
    @Temporal(TemporalType.TIMESTAMP)
    private Date borrado;
    private String borrador;

    public Categoria() {
    }

    public Categoria(Long id, String nombre, String color, String icono, Date creado, String creador, Date modificado, String modificador, Date borrado, String borrador) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.icono = icono;
        this.creado = creado;
        this.creador = creador;
        this.modificado = modificado;
        this.modificador = modificador;
        this.borrado = borrado;
        this.borrador = borrador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public Date getModificado() {
        return modificado;
    }

    public void setModificado(Date modificado) {
        this.modificado = modificado;
    }

    public String getModificador() {
        return modificador;
    }

    public void setModificador(String modificador) {
        this.modificador = modificador;
    }

    public Date getBorrado() {
        return borrado;
    }

    public void setBorrado(Date borrado) {
        this.borrado = borrado;
    }

    public String getBorrador() {
        return borrador;
    }

    public void setBorrador(String borrador) {
        this.borrador = borrador;
    }
}
