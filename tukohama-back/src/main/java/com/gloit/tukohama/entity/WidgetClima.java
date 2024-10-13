package com.gloit.tukohama.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="widget_clima")
public class WidgetClima {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String mode;
  private String label1;
  private String label2;
  private String font;
  private String icon;
  private String day;
  private String theme;

  @Temporal(TemporalType.TIMESTAMP)
  private Date creado;
  private String creador;
  @Temporal(TemporalType.TIMESTAMP)
  private Date modificado;
  private String modificador;
  @Temporal(TemporalType.TIMESTAMP)
  private Date borrado;
  private String borrador;

  public WidgetClima() {
  }

  public WidgetClima(Long id, String mode, String label1, String label2, String font, String icon, String day, String theme, Date creado, String creador, Date modificado, String modificador, Date borrado, String borrador) {
    this.id = id;
    this.mode = mode;
    this.label1 = label1;
    this.label2 = label2;
    this.font = font;
    this.icon = icon;
    this.day = day;
    this.theme = theme;
    this.creado = creado;
    this.creador = creador;
    this.modificado = modificado;
    this.modificador = modificador;
    this.borrado = borrado;
    this.borrador = borrador;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Date getCreado() {
    return creado;
  }

  public void setCreado(Date creado) {
    this.creado = creado;
  }

  public String getCreador() {
    return creador;
  }

  public void setCreador(String creador) {
    this.creador = creador;
  }

  public Date getModificado() {
    return modificado;
  }

  public void setModificado(Date modificado) {
    this.modificado = modificado;
  }

  public String getModificador() {
    return modificador;
  }

  public void setModificador(String modificador) {
    this.modificador = modificador;
  }

  public Date getBorrado() {
    return borrado;
  }

  public void setBorrado(Date borrado) {
    this.borrado = borrado;
  }

  public String getBorrador() {
    return borrador;
  }

  public void setBorrador(String borrador) {
    this.borrador = borrador;
  }
}
