package com.gloit.tukohama.repository;

import com.gloit.tukohama.entity.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SeccionRepository extends JpaRepository<Seccion, Long> {

    @Query("select obj from Seccion as obj where obj.borrado is null and obj.id=:id")
    public Optional<Seccion> findById(@Param("id") Long id);
    @Query("select obj from Seccion as obj where obj.borrado is null ")
    public List<Seccion> findAll();


    @Query("select obj from Seccion as obj where obj.id=:id")
    public Optional<Seccion> findByIdWithDeleted(@Param("id") Long id);
    @Query("select obj from Categoria as obj")
    public List<Seccion> findAllWithDeleted();
}
