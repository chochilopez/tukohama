package com.gloit.tukohama.repository;

import com.gloit.tukohama.entity.Clasificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClasificadoRepository extends JpaRepository<Clasificado, Long> {

    @Query("select obj from Clasificado as obj where obj.borrado is null and obj.id=:id")
    public Optional<Clasificado> findById(@Param("id") Long id);
    @Query("select obj from Clasificado as obj where obj.borrado is null ")
    public List<Clasificado> findAll();


    @Query("select obj from Clasificado as obj where obj.id=:id")
    public Optional<Clasificado> findByIdWithDeleted(@Param("id") Long id);
    @Query("select obj from Clasificado as obj")
    public List<Clasificado> findAllWithDeleted();
}
