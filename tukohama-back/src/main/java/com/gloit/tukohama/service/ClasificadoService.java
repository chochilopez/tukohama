package com.gloit.tukohama.service;

import com.gloit.tukohama.entity.Clasificado;
import java.util.List;
import java.util.Optional;

public interface ClasificadoService {

    public Optional<Clasificado> findById(Long id);
    public List<Clasificado> findAll();

    public Optional<Clasificado> findByIdWithDeleted(Long id);
    public List<Clasificado> findAllWithDeleted();

    public Clasificado insert(Clasificado obj) throws Exception;
    public Clasificado update(Clasificado obj);
    public void delete(Long id);
    public Clasificado recycle(Clasificado obj);
    public void destroy(Long id);
}

