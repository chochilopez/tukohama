package com.gloit.tukohama.repository;

import com.gloit.tukohama.entity.Necrologica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NecrologicaRepository extends JpaRepository<Necrologica, Long> {

    @Query("select obj from Necrologica as obj where obj.borrado is null and obj.id=:id")
    public Optional<Necrologica> findById(@Param("id") Long id);
    @Query("select obj from Necrologica as obj where obj.borrado is null ")
    public List<Necrologica> findAll();


    @Query("select obj from Necrologica as obj where obj.id=:id")
    public Optional<Necrologica> findByIdWithDeleted(@Param("id") Long id);
    @Query("select obj from Necrologica as obj")
    public List<Necrologica> findAllWithDeleted();


}
