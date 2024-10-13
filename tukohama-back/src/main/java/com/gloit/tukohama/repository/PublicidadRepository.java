package com.gloit.tukohama.repository;

import com.gloit.tukohama.entity.Publicidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PublicidadRepository extends JpaRepository<Publicidad, Long> {

    @Query("select obj from Publicidad as obj where obj.borrado is null and obj.id=:id")
    public Optional<Publicidad> findById(@Param("id") Long id);
    @Query("select obj from Publicidad as obj where obj.borrado is null ")
    public List<Publicidad> findAll();


    @Query("select obj from Publicidad as obj where obj.id=:id")
    public Optional<Publicidad> findByIdWithDeleted(@Param("id") Long id);
    @Query("select obj from Publicidad as obj")
    public List<Publicidad> findAllWithDeleted();


}
