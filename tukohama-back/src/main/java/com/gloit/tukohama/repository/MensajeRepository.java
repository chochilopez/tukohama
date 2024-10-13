package com.gloit.tukohama.repository;

import com.gloit.tukohama.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    @Query("select obj from Mensaje as obj where obj.borrado is null and obj.id=:id")
    public Optional<Mensaje> findById(@Param("id") Long id);
    @Query("select obj from Mensaje as obj where obj.borrado is null ")
    public List<Mensaje> findAll();


    @Query("select obj from Mensaje as obj where obj.id=:id")
    public Optional<Mensaje> findByIdWithDeleted(@Param("id") Long id);
    @Query("select obj from Mensaje as obj")
    public List<Mensaje> findAllWithDeleted();
}
