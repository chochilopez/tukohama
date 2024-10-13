package com.gloit.tukohama.service;

import com.gloit.tukohama.entity.Necrologica;
import java.util.List;
import java.util.Optional;

public interface NecrologicaService {

    public Optional<Necrologica> findById(Long id);
    public List<Necrologica> findAll();

    public Optional<Necrologica> findByIdWithDeleted(Long id);
    public List<Necrologica> findAllWithDeleted();

    public Necrologica insert(Necrologica obj) throws Exception;
    public Necrologica update(Necrologica obj);
    public void delete(Long id);
    public Necrologica recycle(Necrologica obj);
    public void destroy(Long id);
}
