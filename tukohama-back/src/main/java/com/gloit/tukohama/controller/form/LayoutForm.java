package com.gloit.tukohama.controller.form;

import com.gloit.tukohama.entity.Layout;
public class LayoutForm {

    private Integer noticiasPortada;

    private Integer cantidadUltimas;

    private Integer tipoFiltradoDestacadas;

    private Integer cantidadDestacadas;

    private Integer categoria1, categoria2, categoria3, categoria4;

    private Integer destacada1, destacada2, destacada3, destacada4;

    public Layout formLayoutDTO(Layout dto){

            dto.setCantidadDestacadas(this.getCantidadDestacadas());
            dto.setTipoFiltradoDestacadas(this.getTipoFiltradoDestacadas());
            dto.setNoticiasPortada(this.getNoticiasPortada());
            dto.setCantidadUltimas(this.getCantidadUltimas());
            dto.setDestacada1(this.getDestacada1());
            dto.setDestacada2(this.getDestacada2());
            dto.setDestacada3(this.getDestacada3());
            dto.setDestacada4(this.getDestacada4());
            dto.setCategoria1(this.getCategoria1());
            dto.setCategoria2(this.getCategoria2());
            dto.setCategoria3(this.getCategoria3());
            dto.setCategoria4(this.getCategoria4());

        return dto;
    }

    public Integer getTipoFiltradoDestacadas() {
        return tipoFiltradoDestacadas;
    }

    public void setTipoFiltradoDestacadas(Integer tipoFiltradoDestacadas) {
        this.tipoFiltradoDestacadas = tipoFiltradoDestacadas;
    }

    public Integer getCantidadDestacadas() {
        return cantidadDestacadas;
    }

    public void setCantidadDestacadas(Integer cantidadDestacadas) {
        this.cantidadDestacadas = cantidadDestacadas;
    }

    public Integer getNoticiasPortada() {
        return noticiasPortada;
    }

    public void setNoticiasPortada(Integer noticiasPortada) {
        this.noticiasPortada = noticiasPortada;
    }

    public Integer getCantidadUltimas() {
        return cantidadUltimas;
    }

    public void setCantidadUltimas(Integer cantidadUltimas) {
        this.cantidadUltimas = cantidadUltimas;
    }

    public Integer getCategoria1() {
        return categoria1;
    }

    public void setCategoria1(Integer categoria1) {
        this.categoria1 = categoria1;
    }

    public Integer getCategoria2() {
        return categoria2;
    }

    public void setCategoria2(Integer categoria2) {
        this.categoria2 = categoria2;
    }

    public Integer getCategoria3() {
        return categoria3;
    }

    public void setCategoria3(Integer categoria3) {
        this.categoria3 = categoria3;
    }

    public Integer getCategoria4() {
        return categoria4;
    }

    public void setCategoria4(Integer categoria4) {
        this.categoria4 = categoria4;
    }

    public Integer getDestacada1() {
        return destacada1;
    }

    public void setDestacada1(Integer destacada1) {
        this.destacada1 = destacada1;
    }

    public Integer getDestacada2() {
        return destacada2;
    }

    public void setDestacada2(Integer destacada2) {
        this.destacada2 = destacada2;
    }

    public Integer getDestacada3() {
        return destacada3;
    }

    public void setDestacada3(Integer destacada3) {
        this.destacada3 = destacada3;
    }

    public Integer getDestacada4() {
        return destacada4;
    }

    public void setDestacada4(Integer destacada4) {
        this.destacada4 = destacada4;
    }
}
