package com.gloit.tukohama.service;

import com.gloit.tukohama.entity.Encuesta;
import java.util.List;
import java.util.Optional;

public interface EncuestaService {

    public Optional<Encuesta> findById(Long id);
    public List<Encuesta> findAll();

    public Optional<Encuesta> findByIdWithDeleted(Long id);
    public List<Encuesta> findAllWithDeleted();

    public Encuesta insert(Encuesta obj) throws Exception;
    public Encuesta update(Encuesta obj);
    public void delete(Long id);
    public Encuesta recycle(Encuesta obj);
    public void destroy(Long id);
}
