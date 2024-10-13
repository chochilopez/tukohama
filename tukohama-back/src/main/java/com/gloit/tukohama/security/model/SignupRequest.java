package com.gloit.tukohama.security.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignupRequest {

  @NotBlank
  @Size(min = 3, max = 50)
  @Email
  private String username;

  @NotBlank
  @Size(max = 50)
  private String nombre;

  @NotBlank
  @Size(max = 50)
  private String apellido;

  @NotBlank
  @Size(min = 8, max = 18)
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}