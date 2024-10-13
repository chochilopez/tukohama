package com.gloit.tukohama.repository;

import com.gloit.tukohama.entity.ItemEncuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemEncuestaRepository extends JpaRepository<ItemEncuesta, Long> {

    @Query("select obj from ItemEncuesta as obj where obj.borrado is null and obj.id=:id")
    public Optional<ItemEncuesta> findById(@Param("id") Long id);
    @Query("select obj from ItemEncuesta as obj where obj.borrado is null ")
    public List<ItemEncuesta> findAll();


    @Query("select obj from ItemEncuesta as obj where obj.id=:id")
    public Optional<ItemEncuesta> findByIdWithDeleted(@Param("id") Long id);
    @Query("select obj from ItemEncuesta as obj")
    public List<ItemEncuesta> findAllWithDeleted();
}
