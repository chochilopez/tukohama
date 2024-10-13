package com.gloit.tukohama.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Layout {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer cantidadUltimas;

  private Integer noticiasPortada;

  private Integer tipoFiltradoDestacadas;

  private Integer cantidadDestacadas;

  private Integer destacada1, destacada2, destacada3, destacada4;

  private Integer categoria1, categoria2, categoria3, categoria4;

  @Temporal(TemporalType.TIMESTAMP)
  private Date creado;
  private String creador;
  @Temporal(TemporalType.TIMESTAMP)
  private Date modificado;
  private String modificador;
  @Temporal(TemporalType.TIMESTAMP)
  private Date borrado;
  private String borrador;

  public Layout() {
  }

  public Layout(Long id, Integer cantidadUltimas, Integer noticiasPortada, Integer tipoFiltradoDestacadas, Integer cantidadDestacadas, Integer destacada1, Integer destacada2, Integer destacada3, Integer destacada4, Integer categoria1, Integer categoria2, Integer categoria3, Integer categoria4, Date creado, String creador, Date modificado, String modificador, Date borrado, String borrador) {
    this.id = id;
    this.cantidadUltimas = cantidadUltimas;
    this.noticiasPortada = noticiasPortada;
    this.tipoFiltradoDestacadas = tipoFiltradoDestacadas;
    this.cantidadDestacadas = cantidadDestacadas;
    this.destacada1 = destacada1;
    this.destacada2 = destacada2;
    this.destacada3 = destacada3;
    this.destacada4 = destacada4;
    this.categoria1 = categoria1;
    this.categoria2 = categoria2;
    this.categoria3 = categoria3;
    this.categoria4 = categoria4;
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

  public Integer getCantidadUltimas() {
    return cantidadUltimas;
  }

  public void setCantidadUltimas(Integer cantidadUltimas) {
    this.cantidadUltimas = cantidadUltimas;
  }

  public Integer getNoticiasPortada() {
    return noticiasPortada;
  }

  public void setNoticiasPortada(Integer noticiasPortada) {
    this.noticiasPortada = noticiasPortada;
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
