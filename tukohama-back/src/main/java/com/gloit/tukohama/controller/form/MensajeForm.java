package com.gloit.tukohama.controller.form;

import com.gloit.tukohama.entity.Mensaje;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MensajeForm {

    @Size(max=100, message = "El path debe tener entre 3 y 100 caracteres")
    @NotEmpty(message = "El nombre en el mensaje no puede ser nulo")
    private String nombre;

    @Email
    @Size(max=100, message = "El email en el mensaje entre 3 y 100 caracteres")
    private String email;

    @Size(max=100, message = "El telefono en el mensaje debe tener entre 3 y 100 caracteres")
    private String telefono;

    @Size(max=100, message = "El cuerpo en el mensaje debe tener entre 3 y 100 caracteres")
    @NotEmpty(message = "El cuerpo en el mensaje no puede ser nulo")
    private String cuerpo;

    public Mensaje formMensajeDTO(Mensaje dto){

        dto.setNombre(this.getNombre());
        if (this.nombre != null)
            dto.setNombre(this.getNombre());
        if (this.email != null)
            dto.setEmail(this.getEmail());
        if (this.telefono != null)
            dto.setTelefono(this.getTelefono());
        dto.setCuerpo(this.getCuerpo());

        return dto;
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
}
