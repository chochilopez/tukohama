package com.gloit.tukohama.repository;

import com.gloit.tukohama.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("select obj from Autor as obj where obj.borrado is null and obj.id=:id")
    public Optional<Autor> findById(@Param("id") Long id);
    @Query("select obj from Autor as obj where obj.borrado is null ")
    public List<Autor> findAll();


    @Query("select obj from Autor as obj where obj.id=:id")
    public Optional<Autor> findByIdWithDeleted(@Param("id") Long id);
    @Query("select obj from Autor as obj")
    public List<Autor> findAllWithDeleted();
}

