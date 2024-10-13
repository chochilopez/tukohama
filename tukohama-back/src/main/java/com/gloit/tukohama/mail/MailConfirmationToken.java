package com.gloit.tukohama.mail;

import com.gloit.tukohama.entity.Usuario;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class MailConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Usuario usuario;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date borrado;

    public MailConfirmationToken() {
    }

    public MailConfirmationToken(Usuario usuario) {
        this.usuario = usuario;
        creado = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public Date getActualizado() {
        return actualizado;
    }

    public void setActualizado(Date actualizado) {
        this.actualizado = actualizado;
    }

    public Date getBorrado() {
        return borrado;
    }

    public void setBorrado(Date borrado) {
        this.borrado = borrado;
    }

    @Override
    public String toString() {
        return "MailConfirmationToken{" +
                "id=" + id +
                ", confirmationToken='" + confirmationToken + '\'' +
                ", usuario=" + usuario +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                ", borrado=" + borrado +
                '}';
    }
}
