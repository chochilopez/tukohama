package com.gloit.tukohama.repository;

import com.gloit.tukohama.entity.WidgetClima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WidgetClimaRepository extends JpaRepository<WidgetClima, Long> {

    @Query("select obj from WidgetClima as obj where obj.borrado is null and obj.id=:id")
    public Optional<WidgetClima> findById(@Param("id") Long id);
    @Query("select obj from WidgetClima as obj where obj.borrado is null ")
    public List<WidgetClima> findAll();


    @Query("select obj from WidgetClima as obj where obj.id=:id")
    public Optional<WidgetClima> findByIdWithDeleted(@Param("id") Long id);
    @Query("select obj from WidgetClima as obj")
    public List<WidgetClima> findAllWithDeleted();
}

