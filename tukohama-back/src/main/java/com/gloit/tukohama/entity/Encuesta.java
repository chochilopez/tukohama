package com.gloit.tukohama.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Encuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Multimedia multimedia;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Temporal(TemporalType.TIMESTAMP)
    private Date publicacion;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    private String creador;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    private String modificador;
    @Temporal(TemporalType.TIMESTAMP)
    private Date borrado;
    private String borrador;

    public Encuesta() {
    }

    public Encuesta(Long id, String titulo, Multimedia multimedia, String descripcion, Date publicacion, Date creado, String creador, Date modificado, String modificador, Date borrado, String borrador) {
        this.id = id;
        this.titulo = titulo;
        this.multimedia = multimedia;
        this.descripcion = descripcion;
        this.publicacion = publicacion;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Multimedia getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Multimedia multimedia) {
        this.multimedia = multimedia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Date publicacion) {
        this.publicacion = publicacion;
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
