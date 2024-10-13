package com.gloit.tukohama.controller.form;

import com.gloit.tukohama.entity.Autor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AutorForm {

    @Size(max=50, message = "El texto del autor debe tener entre 8 y 50 caracteres")
    @NotEmpty(message = "El nombre del autor no puede ser nulo")
    private String nombre;

    public Autor formAutorDTO(Autor dto){

        dto.setNombre(this.getNombre());

        return dto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
