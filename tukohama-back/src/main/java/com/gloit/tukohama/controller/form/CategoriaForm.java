package com.gloit.tukohama.controller.form;

import com.gloit.tukohama.entity.Categoria;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CategoriaForm {

    @Size(max=50, message = "El texto de la categoria debe tener entre 3 y 50 caracteres")
    @NotEmpty(message = "El nombre de la categoria no puede ser nulo")
    private String nombre;

    @Size(min = 3, max=50, message = "El texto de la categoria debe tener entre 3 y 50 caracteres")
    private String icono;

    @Size(max=50, message = "El texto de la categoria debe tener entre 3 y 50 caracteres")
    @NotEmpty(message = "El nombre de la categoria no puede ser nulo")
    private String color;

    public Categoria formCategoriaDTO(Categoria dto){

        dto.setNombre(this.getNombre());
        dto.setColor(this.getColor());
        if (this.icono!=null)
            dto.setIcono(this.getIcono());

        return dto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
