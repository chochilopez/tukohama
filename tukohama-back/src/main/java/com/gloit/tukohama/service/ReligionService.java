package com.gloit.tukohama.service;

import com.gloit.tukohama.entity.Religion;

import java.util.List;
import java.util.Optional;

public interface ReligionService {

    public Optional<Religion> findById(Long id);
    public List<Religion> findAll();

    public Optional<Religion> findByIdWithDeleted(Long id);
    public List<Religion> findAllWithDeleted();

    public Religion insert(Religion obj) throws Exception;
    public Religion update(Religion obj);
    public void delete(Long id);
    public Religion recycle(Religion obj);
    public void destroy(Long id);
}
