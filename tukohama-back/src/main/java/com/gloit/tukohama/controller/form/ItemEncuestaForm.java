package com.gloit.tukohama.controller.form;

import com.gloit.tukohama.entity.ItemEncuesta;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ItemEncuestaForm {

    @Size(max=50, message = "El texto del clasificado debe tener entre 3 y 50 caracteres")
    @NotEmpty(message = "El texto del clasificado no puede ser nulo")
    private String item;

    private Integer votos;

    public ItemEncuesta formItemEncuestaDTO(ItemEncuesta dto){

        dto.setItem(this.getItem());
        if (this.votos!=null)
            dto.setVotos(this.getVotos());

        return dto;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getVotos() {
        return votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

}
