package com.gloit.tukohama.repository;

import com.gloit.tukohama.entity.Layout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LayoutRepository extends JpaRepository<Layout, Long> {

    @Query("select obj from Layout as obj where obj.borrado is null and obj.id=:id")
    public Optional<Layout> findById(@Param("id") Long id);
    @Query("select obj from Layout as obj where obj.borrado is null ")
    public List<Layout> findAll();


    @Query("select obj from Layout as obj where obj.id=:id")
    public Optional<Layout> findByIdWithDeleted(@Param("id") Long id);
    @Query("select obj from Layout as obj")
    public List<Layout> findAllWithDeleted();
}

