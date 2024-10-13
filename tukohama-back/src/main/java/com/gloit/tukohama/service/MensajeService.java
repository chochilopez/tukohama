package com.gloit.tukohama.service;

import com.gloit.tukohama.entity.Mensaje;

import java.util.List;
import java.util.Optional;

public interface MensajeService {

    public Optional<Mensaje> findById(Long id);
    public List<Mensaje> findAll();

    public Optional<Mensaje> findByIdWithDeleted(Long id);
    public List<Mensaje> findAllWithDeleted();

    public Mensaje insert(Mensaje obj) throws Exception;
    public Mensaje update(Mensaje obj);
    public void delete(Long id);
    public Mensaje recycle(Mensaje obj);
    public void destroy(Long id);
}
