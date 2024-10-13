package com.gloit.tukohama.controller.form;

import com.gloit.tukohama.entity.Clasificado;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

public class ClasificadoForm {

    @Size(max=100, message = "El texto debe tener entre 3 y 50 caracteres")
    @NotEmpty(message = "El titulo del clasificado no puede ser nulo")
    private String titulo;

    @Size(max=1000, message = "El cuerpo debe tener entre 3 y 1000 caracteres")
    @NotEmpty(message = "El cuerpo del clasificado no puede ser nulo")
    private String cuerpo;

    @Size(max=100, message = "El texto debe tener entre 3 y 50 caracteres")
    @NotEmpty(message = "El contacto del clasificado no puede ser nulo")
    private String contacto;

    public Clasificado formClasificadoDTO(Clasificado dto){

        dto.setTitulo(this.getTitulo());
        dto.setCuerpo(this.getCuerpo());
        dto.setCuerpo(this.getCuerpo());

        return dto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
}
