package com.gloit.tukohama.controller.form;

import com.gloit.tukohama.entity.Multimedia;
import com.gloit.tukohama.entity.enumerator.EnumTipoArchivo;

import javax.validation.constraints.Size;

public class MultimediaForm {

    @Size(min = 3, max=100, message = "El path debe tener entre 3 y 100 caracteres")
    private String path;

    @Size(min = 3, max=200, message = "La descripcion debe tener entre 3 y 200 caracteres")
    private String descripcion;

    @Size(min = 3, max=50, message = "El nombre de archivo debe tener entre 3 y 50 caracteres")
    private String nombreArchivo;

    private String tamanio;

    public Multimedia formImagenDTO(Multimedia dto){

        if (this.path!=null)
            dto.setPath(this.getPath());
        if (this.tamanio!=null)
            dto.setTamanio(this.getTamanio());
        if(this.nombreArchivo!=null)
            dto.setNombreArchivo(this.getNombreArchivo());
        if (this.descripcion!=null)
            dto.setDescripcion(this.getDescripcion());

        return dto;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
