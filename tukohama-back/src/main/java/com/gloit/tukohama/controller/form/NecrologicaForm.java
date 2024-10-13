package com.gloit.tukohama.controller.form;

import com.gloit.tukohama.entity.Necrologica;
import com.gloit.tukohama.entity.Religion;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

public class NecrologicaForm {

    @Size(max=50, message = "El nombre de la necrologica debe tener entre 3 y 50 caracteres")
    @NotEmpty(message = "El nombre de la necrologica no puede ser nulo")
    private String nombre;

    private Religion religion;

    private Long idReligion;

    @Size(max=500, message = "El cuerpo de la necrologica debe tener entre 3 y 500 caracteres")
    @NotEmpty(message = "El cuerpo de la necrologica no puede ser nulo")
    private String cuerpo;

    public Necrologica formNecrologicaDTO(Necrologica dto){

        dto.setNombre(this.getNombre());
        if (this.religion!=null)
            dto.setReligion(this.getReligion());
        dto.setCuerpo(this.getCuerpo());

        return dto;
    }

    public Long getIdReligion() {
        return idReligion;
    }

    public void setIdReligion(Long idReligion) {
        this.idReligion = idReligion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
}
