package com.gloit.tukohama.controller.form;

import com.gloit.tukohama.entity.Publicidad;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PublicidadForm {

    @Size(max=50, message = "El texto de la publicidad debe tener entre 3 y 50 caracteres")
    @NotEmpty(message = "El nombre de la publicidad no puede ser nulo")
    private String firma;

    @Size(max=50, message = "El texto de la link debe tener entre 3 y 50 caracteres")
    private String link;

    @Size(max=50, message = "El texto de la nombreArchivo debe tener entre 3 y 50 caracteres")
    @NotEmpty(message = "El nombre de la nombreArchivo no puede ser nulo")
    private String nombreArchivo;

    private Integer tipo;

    private Integer importancia;

    public Publicidad formPublicidadDTO(Publicidad dto){

        dto.setFirma(this.getFirma());
        if (this.link !=null)
            dto.setLink(this.getLink());
        dto.setNombreArchivo(this.getNombreArchivo());
        if (this.importancia !=null)
            dto.setImportancia(this.getImportancia());
        if (this.tipo!=null)
            dto.setTipo(this.getTipo());

        return dto;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public Integer getImportancia() {
        return importancia;
    }

    public void setImportancia(Integer importancia) {
        this.importancia = importancia;
    }
}
