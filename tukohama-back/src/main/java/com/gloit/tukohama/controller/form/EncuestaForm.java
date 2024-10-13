package com.gloit.tukohama.controller.form;

import com.gloit.tukohama.entity.Encuesta;
import com.gloit.tukohama.entity.Multimedia;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

public class EncuestaForm {

    @Size(max=100, message = "El titulo debe tener entre 3 y 50 caracteres")
    @NotEmpty(message = "El titulo del clasificado no puede ser nulo")
    private String titulo;

    private Long id_imagen;

    private Multimedia multimedia;

    @Size(max=1000, message = "El descripcion debe tener entre 1000 caracteres")
    @NotEmpty(message = "El descripcion del clasificado no puede ser nulo")
    private String descripcion;

    public Encuesta formEncuestaDTO(Encuesta dto){

        dto.setTitulo(this.getTitulo());
        if (this.multimedia !=null)
            dto.setMultimedia(this.getMultimedia());
        dto.setDescripcion(this.getDescripcion());

        return dto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId_imagen() {
        return id_imagen;
    }

    public void setId_imagen(Long id_imagen) {
        this.id_imagen = id_imagen;
    }

    public Multimedia getImagen() {
        return multimedia;
    }

    public void setImagen(Multimedia multimedia) {
        this.multimedia = multimedia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Multimedia getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Multimedia multimedia) {
        this.multimedia = multimedia;
    }
}
