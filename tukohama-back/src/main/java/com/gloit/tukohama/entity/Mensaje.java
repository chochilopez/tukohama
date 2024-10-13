package com.gloit.tukohama.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Mensaje {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;

  private String email;

  private String telefono;

  @Column(columnDefinition = "TEXT")
  private String cuerpo;

  @Temporal(TemporalType.TIMESTAMP)
  private Date creado;
  private String creador;
  @Temporal(TemporalType.TIMESTAMP)
  private Date modificado;
  private String modificador;
  @Temporal(TemporalType.TIMESTAMP)
  private Date borrado;
  private String borrador;

  public Mensaje() {
  }

  public Mensaje(Long id, String nombre, String email, String telefono, String cuerpo, Date creado, String creador, Date modificado, String modificador, Date borrado, String borrador) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
    this.telefono = telefono;
    this.cuerpo = cuerpo;
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

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getCuerpo() {
    return cuerpo;
  }

  public void setCuerpo(String cuerpo) {
    this.cuerpo = cuerpo;
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
