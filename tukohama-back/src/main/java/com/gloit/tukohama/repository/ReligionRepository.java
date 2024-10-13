package com.gloit.tukohama.repository;

import com.gloit.tukohama.entity.Religion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReligionRepository extends JpaRepository<Religion, Long> {

    @Query("select obj from Religion as obj where obj.borrado is null and obj.id=:id")
    public Optional<Religion> findById(@Param("id") Long id);
    @Query("select obj from Religion as obj where obj.borrado is null ")
    public List<Religion> findAll();


    @Query("select obj from Religion as obj where obj.id=:id")
    public Optional<Religion> findByIdWithDeleted(@Param("id") Long id);
    @Query("select obj from Religion as obj")
    public List<Religion> findAllWithDeleted();


}
