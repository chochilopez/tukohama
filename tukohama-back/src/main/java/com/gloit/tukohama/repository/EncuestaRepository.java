package com.gloit.tukohama.repository;

import com.gloit.tukohama.entity.Encuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EncuestaRepository extends JpaRepository<Encuesta, Long> {

    @Query("select obj from Encuesta as obj where obj.borrado is null and obj.id=:id")
    public Optional<Encuesta> findById(@Param("id") Long id);
    @Query("select obj from Encuesta as obj where obj.borrado is null ")
    public List<Encuesta> findAll();


    @Query("select obj from Encuesta as obj where obj.id=:id")
    public Optional<Encuesta> findByIdWithDeleted(@Param("id") Long id);
    @Query("select obj from Encuesta as obj")
    public List<Encuesta> findAllWithDeleted();
}

