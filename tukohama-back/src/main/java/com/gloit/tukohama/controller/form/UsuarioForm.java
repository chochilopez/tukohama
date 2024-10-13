package com.gloit.tukohama.controller.form;

import com.gloit.tukohama.entity.Usuario;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;

public class UsuarioForm {

    @Length(min= 3, max= 50, message = "El nombre del usuario debe tener entre 3 y 50 caracteres")
    private String nombre;

    @Length(min= 3, max= 50, message = "El apellido del usuario debe tener entre 3 y 50 caracteres")
    private String apellido;

    @Email(message = "Se ingreso una direccion de email no valida")
    @Length(min= 3, max= 50, message = "El email del usuario debe tener entre 3 y 50 caracteres")
    private String email;

    @Length(min= 3, max= 20, message = "El nombre de usuario debe tener entre 3 y 20 caracteres")
    private String username;

    @Length(min= 8, max= 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    private String password;

    private Boolean habilitado;


    public Usuario formUsuarioDTO(Usuario dto){

        if (this.nombre!=null)
            dto.setNombre(this.nombre);
        if (this.apellido!=null)
            dto.setApellido(this.apellido);
        if (this.email!=null)
            dto.setEmail(this.email);
        if (this.username!=null)
            dto.setUsername(this.username);
        if (this.password!=null)
            dto.setPassword(this.password);
        if (this.habilitado!=null)
            dto.setHabilitado(this.habilitado);

        return dto;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }
}
