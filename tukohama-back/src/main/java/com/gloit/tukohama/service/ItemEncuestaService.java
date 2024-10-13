package com.gloit.tukohama.service;

import com.gloit.tukohama.entity.ItemEncuesta;
import java.util.List;
import java.util.Optional;

public interface ItemEncuestaService {

    public Optional<ItemEncuesta> findById(Long id);
    public List<ItemEncuesta> findAll();

    public Optional<ItemEncuesta> findByIdWithDeleted(Long id);
    public List<ItemEncuesta> findAllWithDeleted();

    public ItemEncuesta insert(ItemEncuesta obj) throws Exception;
    public ItemEncuesta update(ItemEncuesta obj);
    public void delete(Long id);
    public ItemEncuesta recycle(ItemEncuesta obj);
    public void destroy(Long id);
}
