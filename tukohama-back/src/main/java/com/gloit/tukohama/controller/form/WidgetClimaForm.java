package com.gloit.tukohama.controller.form;

import com.gloit.tukohama.entity.WidgetClima;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class WidgetClimaForm {

    private String mode;
    private String label1;
    private String label2;
    private String font;
    private String icon;
    private String day;
    private String theme;

    public WidgetClima formWidgetClimaDTO(WidgetClima dto){
        dto.setMode(this.getMode());
        dto.setLabel1(this.getLabel1());
        dto.setLabel2(this.getLabel2());
        dto.setFont(this.getFont());
        dto.setIcon(this.getIcon());
        dto.setDay(this.getDay());
        dto.setTheme(this.getTheme());

        return dto;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
