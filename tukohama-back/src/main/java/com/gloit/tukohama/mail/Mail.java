package com.gloit.tukohama.mail;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

@Entity
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String emisor;
    private String receptor;
    private String asunto;
    private Boolean enviado;

    @Column(columnDefinition = "TEXT")
    private String texto;

    @Column(columnDefinition = "TEXT")
    private String error;

    @Transient
    private File archivo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date envio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date borrado;

    public Mail() {
    }

    public Mail(String nombre,String emisor, String receptor, String asunto, String texto, Date envio) {
        this.nombre=nombre;
        this.emisor = emisor;
        this.receptor = receptor;
        this.asunto = asunto;
        this.texto = texto;
        this.envio=envio;
    }

    public Mail(String nombre, String emisor, String receptor, String asunto, String texto, File archivo, Date envio) {
        this.nombre=nombre;
        this.emisor = emisor;
        this.receptor = receptor;
        this.asunto = asunto;
        this.texto = texto;
        this.archivo = archivo;
        this.envio=envio;
    }

    public Mail(Long id, String nombre, String emisor, String receptor, String asunto, Boolean enviado, String texto, String error, File archivo, Date envio, Date creado, Date actualizado, Date borrado) {
        this.id = id;
        this.nombre=nombre;
        this.emisor = emisor;
        this.receptor = receptor;
        this.asunto = asunto;
        this.enviado = enviado;
        this.texto = texto;
        this.error = error;
        this.archivo = archivo;
        this.envio = envio;
        this.creado = creado;
        this.actualizado = actualizado;
        this.borrado = borrado;
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

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Boolean getEnviado() {
        return enviado;
    }

    public void setEnviado(Boolean enviado) {
        this.enviado = enviado;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public Date getEnvio() {
        return envio;
    }

    public void setEnvio(Date envio) {
        this.envio = envio;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public Date getActualizado() {
        return actualizado;
    }

    public void setActualizado(Date actualizado) {
        this.actualizado = actualizado;
    }

    public Date getBorrado() {
        return borrado;
    }

    public void setBorrado(Date borrado) {
        this.borrado = borrado;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", emisor='" + emisor + '\'' +
                ", receptor='" + receptor + '\'' +
                ", asunto='" + asunto + '\'' +
                ", enviado=" + enviado +
                ", texto='" + texto + '\'' +
                ", error='" + error + '\'' +
                ", archivo=" + archivo +
                ", envio=" + envio +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                ", borrado=" + borrado +
                '}';
    }
}