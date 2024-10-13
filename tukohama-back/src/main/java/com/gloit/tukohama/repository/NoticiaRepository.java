package com.gloit.tukohama.repository;

import com.gloit.tukohama.entity.Noticia;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

    @Query("select obj from Noticia as obj where obj.borrado is null and obj.id=:id")
    public Optional<Noticia> findById(@Param("id") Long id);

    @Query("select obj from Noticia as obj where obj.borrado is null ")
    public List<Noticia> findAll();

    @Query("select obj from Noticia as obj where obj.borrado is null and obj.publicado BETWEEN :inicio AND :fin")
    public List<Noticia> buscarPublicadaPorTiempo(@Param("inicio") Date inicio, @Param("fin") Date fin);

    @Query("select obj from Noticia as obj where obj.borrado is null and obj.publicado is not null and obj.titulo like %:titulo%")
    public List<Noticia> buscarPorTituloPublicada(@Param("titulo")String titulo);

    @Query("select obj from Noticia as obj where obj.id=:id")
    public Optional<Noticia> findByIdWithDeleted(@Param("id") Long id);

    @Query("select obj from Noticia as obj")
    public List<Noticia> findAllWithDeleted();

    @Query("select obj from Noticia as obj where obj.borrado is null and obj.publicado is not null and obj.id=:id")
    public Optional<Noticia> buscarPorIdPublicada(@Param("id") Long id);

    @Query("select obj from Noticia as obj where obj.borrado is null and obj.publicado is not null")
    public List<Noticia> buscarPublicadas();

    @Query("select obj from Noticia as obj where obj.borrado is null and obj.publicado is null")
    public List<Noticia> buscarBorradores();

    @Query("select obj from Noticia as obj where obj.borrado is not null")
    public List<Noticia> buscarBorradas();

    public List<Noticia> findFirst6ByCategoriaNombreOrderByPublicadoDesc(@Param("categoria") String categoria);

    @Query("select obj from Noticia as obj where obj.borrado is null and obj.publicado is not null and obj.categoria.nombre=:categoria")
    public List<Noticia> buscarPorCategoriaPublicadas(@Param("categoria") String categoria);

    @Query("select obj from Noticia as obj where obj.borrado is null and obj.publicado is not null and obj.portada is true and importancia=:importancia")
    public List<Noticia> buscarPorImportanciaPublicadasPortada(@Param("importancia") Integer importancia);

    @Query("select obj from Noticia as obj where obj.borrado is null and obj.publicado is not null and obj.portada=true")
    public List<Noticia> buscarPublicadasPortada();

    @Query("select COUNT(obj) from Noticia as obj where obj.borrado is null and obj.publicado is not null")
    public Long cantidadPublicadas();

    @Query("select COUNT(obj) from Noticia as obj where obj.borrado is null and obj.publicado is null")
    public Long cantidadBorradores();
}
