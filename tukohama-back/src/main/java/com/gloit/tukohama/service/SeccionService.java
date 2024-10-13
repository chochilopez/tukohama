package com.gloit.tukohama.service;

import com.gloit.tukohama.entity.Seccion;

import java.util.List;
import java.util.Optional;

public interface SeccionService {

    public Optional<Seccion> findById(Long id);
    public List<Seccion> findAll();

    public Optional<Seccion> findByIdWithDeleted(Long id);
    public List<Seccion> findAllWithDeleted();

    public Seccion insert(Seccion obj) throws Exception;
    public Seccion update(Seccion obj);
    public void delete(Long id);
    public Seccion recycle(Seccion obj);
    public void destroy(Long id);
}
