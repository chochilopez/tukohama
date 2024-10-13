package com.gloit.tukohama.controller.form;

import com.gloit.tukohama.entity.Seccion;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SeccionForm {

    @Size(min = 3, max=50, message = "El texto de la categoria debe tener entre 3 y 50 caracteres")
    @NotEmpty(message = "El nombre de la categoria no puede ser nulo")
    private String nombre;

    private String color;

    public Seccion formSeccionDTO(Seccion dto){

        dto.setNombre(this.getNombre());
        if (this.color!=null)
            dto.setColor(this.getColor());

        return dto;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
