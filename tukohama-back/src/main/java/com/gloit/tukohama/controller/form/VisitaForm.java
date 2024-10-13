package com.gloit.tukohama.controller.form;

import com.gloit.tukohama.entity.Visita;

public class VisitaForm {

    private String ip;

    public Visita formVisitaDTO(Visita dto){

        if (this.ip!=null)
            dto.setIp(this.getIp());

        return dto;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
