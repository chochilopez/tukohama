package com.gloit.tukohama.service;

import com.gloit.tukohama.entity.Noticia;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface NoticiaService {

    public Long cantidadPublicadas();
    public Long cantidadBorradores();

    public Optional<Noticia> buscarPorIdPublicada(Long id);
    public Noticia publicar(Long id);
    public Noticia despublicar(Long id);
    public Noticia addVisita(Long id);
    public List<Noticia>buscarNoticiasMasVistas(Integer cantidad);

    public List<Noticia> buscarPublicadaPorTiempo(String inicio, String fin) throws Exception;
    public List<Noticia> buscarPorTituloPublicada(String titulo);
    public List<Noticia> buscarPublicadas();
    public List<Noticia> buscarBorradas();
    public List<Noticia> buscarBorradores();
    public List<Noticia> buscarPublicadasPortada();
    public List<Noticia> buscarPorImportanciaPublicadasPortada(Integer importancia);
    public List<Noticia> buscarPorCategoriaPublicadas(String categoria);
    public List<Noticia> buscarPorCategoriaPublicadasPortada(String categoria, Integer cantidad);

    public Optional<Noticia> findById(Long id);
    public List<Noticia> findAll();

    public Optional<Noticia> findByIdWithDeleted(Long id);
    public List<Noticia> findAllWithDeleted();

    public Noticia insert(Noticia obj) throws Exception;
    public Noticia update(Noticia obj);
    public void delete(Long id);
    public Noticia recycle(Noticia obj);
    public void destroy(Long id);
}
