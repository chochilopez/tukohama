package com.gloit.tukohama.repository;

import com.gloit.tukohama.entity.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VisitaRepository extends JpaRepository<Visita, Long> {

    @Query("select obj from Visita as obj where obj.borrado is null and obj.id=:id")
    public Optional<Visita> findById(@Param("id") Long id);
    @Query("select obj from Visita as obj where obj.borrado is null ")
    public List<Visita> findAll();


    @Query("select obj from Visita as obj where obj.id=:id")
    public Optional<Visita> findByIdWithDeleted(@Param("id") Long id);
    @Query("select obj from Visita as obj")
    public List<Visita> findAllWithDeleted();


    @Query("select obj from Visita obj where obj.ip=:ip")
    public Optional<Visita> buscarPorIp(@Param("ip") String ip);
}
