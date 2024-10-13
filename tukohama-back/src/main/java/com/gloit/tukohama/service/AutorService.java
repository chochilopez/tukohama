package com.gloit.tukohama.service;

import com.gloit.tukohama.entity.Autor;

import java.util.List;
import java.util.Optional;

public interface AutorService {

    public Optional<Autor> findById(Long id);
    public List<Autor> findAll();

    public Optional<Autor> findByIdWithDeleted(Long id);
    public List<Autor> findAllWithDeleted();

    public Autor insert(Autor obj) throws Exception;
    public Autor update(Autor obj);
    public void delete(Long id);
    public Autor recycle(Autor obj);
    public void destroy(Long id);
}
