package com.gloit.tukohama.service;

import com.gloit.tukohama.entity.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    public Optional<Categoria> findById(Long id);
    public List<Categoria> findAll();

    public Optional<Categoria> findByIdWithDeleted(Long id);
    public List<Categoria> findAllWithDeleted();

    public Categoria insert(Categoria obj) throws Exception;
    public Categoria update(Categoria obj);
    public void delete(Long id);
    public Categoria recycle(Categoria obj);
    public void destroy(Long id);
}
