package com.gloit.tukohama.controller.form;

import com.gloit.tukohama.entity.Autor;
import com.gloit.tukohama.entity.Categoria;
import com.gloit.tukohama.entity.Multimedia;
import com.gloit.tukohama.entity.Noticia;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class NoticiaForm {

    private String titulo;

    private String copete;

    private String cuerpo;

    private String meta;

    private Integer importancia;

    private Boolean publicable;

    private Boolean comentarios;

    private Boolean portada;

    private Categoria categoria;

    private Long idCategoria;

    private Autor autor;

    private Long idAutor;

    private List<Long> idsAudios;

    private List<Long> idsImagenes;

    private List<Long> idsVideos;

    private Long idImagenPortada;

    private Multimedia imagenPortada;

    private Set<Multimedia> audios;

    private Set<Multimedia> imagenes;

    private Set<Multimedia> videos;


    public Noticia formNoticiaDTO(Noticia dto){

        if (this.titulo!=null)
            dto.setTitulo(this.titulo);
        if (this.portada!=null)
            dto.setPortada(this.getPortada());
        if (this.comentarios!=null)
            dto.setComentarios(this.getComentarios());
        if (this.meta!=null)
            dto.setMeta(this.meta);
        if (this.copete!=null)
            dto.setCopete(this.copete);
        if (this.cuerpo!=null)
            dto.setCuerpo(this.cuerpo);
        if (this.autor!=null)
            dto.setAutor(this.autor);
        if (this.portada!=null)
            dto.setPortada(this.portada);
        if (this.categoria!=null)
            dto.setCategoria(this.categoria);
        if(this.imagenPortada!=null)
            dto.setImagenPortada(this.imagenPortada);
        if(this.audios!=null)
            dto.setAudios(this.getAudios());
        if(this.imagenes!=null)
            dto.setImagenes(this.getImagenes());
        if(this.videos!=null)
            dto.setVideos(this.getVideos());
        if (this.importancia!=null)
            dto.setImportancia(this.importancia);
        if (this.publicable!=null)
            dto.setPublicable(this.getPublicable());

        return dto;
    }

    public List<Long> getIdsAudios() {
        return idsAudios;
    }

    public void setIdsAudios(List<Long> idsAudios) {
        this.idsAudios = idsAudios;
    }

    public List<Long> getIdsImagenes() {
        return idsImagenes;
    }

    public void setIdsImagenes(List<Long> idsImagenes) {
        this.idsImagenes = idsImagenes;
    }

    public List<Long> getIdsVideos() {
        return idsVideos;
    }

    public void setIdsVideos(List<Long> idsVideos) {
        this.idsVideos = idsVideos;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getPublicable() {
        return publicable;
    }

    public void setPublicable(Boolean publicable) {
        this.publicable = publicable;
    }

    public String getCopete() {
        return copete;
    }

    public void setCopete(String copete) {
        this.copete = copete;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public Integer getImportancia() {
        return importancia;
    }

    public void setImportancia(Integer importancia) {
        this.importancia = importancia;
    }

    public Boolean getComentarios() {
        return comentarios;
    }

    public void setComentarios(Boolean comentarios) {
        this.comentarios = comentarios;
    }

    public Boolean getPortada() {
        return portada;
    }

    public void setPortada(Boolean portada) {
        this.portada = portada;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
    }

    public Long getIdImagenPortada() {
        return idImagenPortada;
    }

    public void setIdImagenPortada(Long idImagenPortada) {
        this.idImagenPortada = idImagenPortada;
    }

    public Multimedia getImagenPortada() {
        return imagenPortada;
    }

    public void setImagenPortada(Multimedia imagenPortada) {
        this.imagenPortada = imagenPortada;
    }

}
