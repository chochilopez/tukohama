package com.gloit.tukohama.repository;

import com.gloit.tukohama.entity.Multimedia;
import com.gloit.tukohama.entity.enumerator.EnumTipoArchivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MultimediaRepository extends JpaRepository<Multimedia, Long> {

    @Query("select obj from Multimedia as obj where obj.borrado is null and obj.id=:id")
    public Optional<Multimedia> findById(@Param("id") Long id);
    @Query("select obj from Multimedia as obj where obj.borrado is null ")
    public List<Multimedia> findAll();


    @Query("select obj from Multimedia as obj where obj.id=:id")
    public Optional<Multimedia> findByIdWithDeleted(@Param("id") Long id);
    @Query("select obj from Multimedia as obj")
    public List<Multimedia> findAllWithDeleted();

    @Query("select obj from Multimedia as obj where obj.borrado is null and obj.tipo=:tipo")
    public List<Multimedia> buscarMultimedia(@Param("tipo") Integer tipo);
}

