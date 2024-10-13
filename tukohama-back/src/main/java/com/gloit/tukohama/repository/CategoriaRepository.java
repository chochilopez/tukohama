package com.gloit.tukohama.repository;

import com.gloit.tukohama.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("select obj from Categoria as obj where obj.borrado is null and obj.id=:id")
    public Optional<Categoria> findById(@Param("id") Long id);
    @Query("select obj from Categoria as obj where obj.borrado is null ")
    public List<Categoria> findAll();


    @Query("select obj from Categoria as obj where obj.id=:id")
    public Optional<Categoria> findByIdWithDeleted(@Param("id") Long id);
    @Query("select obj from Categoria as obj")
    public List<Categoria> findAllWithDeleted();
}
