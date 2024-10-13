package com.gloit.tukohama.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String copete;

    @Column(columnDefinition = "TEXT")
    private String meta;

    @Column(columnDefinition = "TEXT")
    private String cuerpo;

    //TODO Facebook plugins Comentarios Disqus - Ver api google analytics
    private Boolean comentarios;

    private Integer importancia;

    //Si esta publicada no esta en borrador, y viceversa
    private Boolean publicable;

    private Boolean portada;

    private Integer vistas;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Multimedia imagenPortada;

    @OneToMany
    private Set<Multimedia> audios;

    @OneToMany
    private Set<Multimedia> imagenes;

    @OneToMany
    private Set<Multimedia> videos;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Categoria.class, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Autor.class, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date publicado;
    private String publicador;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    private String creador;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    private String modificador;
    @Temporal(TemporalType.TIMESTAMP)
    private Date borrado;
    private String borrador;

    public Noticia() {
    }

    public Noticia(Long id, String titulo, String copete, String meta, String cuerpo, Boolean comentarios, Integer importancia, Boolean publicable, Boolean portada, Integer vistas, Multimedia imagenPortada, Set<Multimedia> audios, Set<Multimedia> imagenes, Set<Multimedia> videos, Categoria categoria, Autor autor, Date publicado, String publicador, Date creado, String creador, Date modificado, String modificador, Date borrado, String borrador) {
        this.id = id;
        this.titulo = titulo;
        this.copete = copete;
        this.meta = meta;
        this.cuerpo = cuerpo;
        this.comentarios = comentarios;
        this.importancia = importancia;
        this.publicable = publicable;
        this.portada = portada;
        this.vistas = vistas;
        this.imagenPortada = imagenPortada;
        this.audios = audios;
        this.imagenes = imagenes;
        this.videos = videos;
        this.categoria = categoria;
        this.autor = autor;
        this.publicado = publicado;
        this.publicador = publicador;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCopete() {
        return copete;
    }

    public void setCopete(String copete) {
        this.copete = copete;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Boolean getComentarios() {
        return comentarios;
    }

    public void setComentarios(Boolean comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getImportancia() {
        return importancia;
    }

    public void setImportancia(Integer importancia) {
        this.importancia = importancia;
    }

    public Boolean getPublicable() {
        return publicable;
    }

    public void setPublicable(Boolean publicable) {
        this.publicable = publicable;
    }

    public Boolean getPortada() {
        return portada;
    }

    public void setPortada(Boolean portada) {
        this.portada = portada;
    }

    public Integer getVistas() {
        return vistas;
    }

    public void setVistas(Integer vistas) {
        this.vistas = vistas;
    }

    public Multimedia getImagenPortada() {
        return imagenPortada;
    }

    public void setImagenPortada(Multimedia imagenPortada) {
        this.imagenPortada = imagenPortada;
    }

    public Set<Multimedia> getAudios() {
        return audios;
    }

    public void setAudios(Set<Multimedia> audios) {
        this.audios = audios;
    }

    public Set<Multimedia> getImagenes() {
        return imagenes;
    }

    public void setImagenes(Set<Multimedia> imagenes) {
        this.imagenes = imagenes;
    }

    public Set<Multimedia> getVideos() {
        return videos;
    }

    public void setVideos(Set<Multimedia> videos) {
        this.videos = videos;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Date getPublicado() {
        return publicado;
    }

    public void setPublicado(Date publicado) {
        this.publicado = publicado;
    }

    public String getPublicador() {
        return publicador;
    }

    public void setPublicador(String publicador) {
        this.publicador = publicador;
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
