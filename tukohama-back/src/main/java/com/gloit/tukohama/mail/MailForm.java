package com.gloit.tukohama.mail;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.File;

public class MailForm {

    @Positive(message = "EL id no puede ser menor que 0")
    private Long id;

    @Length(min = 3, max=60, message = "El nombre de usuario debe tener entre 3 y 60 caracteres")
    @NotEmpty(message = "El nombre de usuario no puede ser nulo")
    private String nombre;

    @Length(min = 3, max=50, message = "El nombre de usuario debe tener entre 3 y 60 caracteres")
    @NotEmpty(message = "El nombre de usuario no puede ser nulo")
    private String emisor;

    @Length(min = 3, max=50, message = "El receptor debe tener entre 3 y 50 caracteres")
    @NotEmpty(message = "El receptor no puede ser nulo")
    private String receptor;

    @Length(min = 3, max=50, message = "El asunto debe tener entre 3 y 50 caracteres")
    @NotEmpty(message = "El asunto no puede ser nulo")
    private String asunto;

    @Length(min = 3, max=250, message = "El texto debe tener entre 3 y 250 caracteres")
    @NotEmpty(message = "El texto no puede ser nulo")
    private String texto;

    private File archivo;

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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public Mail toPojo() {
        Mail pojo = new Mail();

        if (this.id !=null)
            pojo.setId(this.getId());
        pojo.setNombre(this.getNombre());
        pojo.setEmisor(this.getEmisor());
        pojo.setReceptor(this.getReceptor());
        pojo.setAsunto(this.getAsunto());
        pojo.setTexto(this.getTexto());
        if (this.archivo !=null)
            pojo.setArchivo(this.getArchivo());

        return pojo;
    }
}
