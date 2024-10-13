package com.gloit.tukohama.controller.form;

import com.gloit.tukohama.entity.Religion;

public class ReligionForm {

    private String nombre;

    private String icono;

    public Religion formReligionDTO(Religion dto){

        if (this.nombre!=null)
            dto.setNombre(this.getNombre());
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
}
