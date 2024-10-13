package com.gloit.tukohama.service;

import com.gloit.tukohama.entity.Visita;

import java.util.List;
import java.util.Optional;

public interface VisitaService {

    public Long cantidad();
    public Optional<Visita> buscarPorIp(String ip);

    public Optional<Visita> findById(Long id);
    public List<Visita> findAll();

    public Optional<Visita> findByIdWithDeleted(Long id);
    public List<Visita> findAllWithDeleted();

    public Visita insert(Visita obj) throws Exception;
    public Visita update(Visita obj);
    public void delete(Long id);
    public Visita recycle(Visita obj);
    public void destroy(Long id);
}
