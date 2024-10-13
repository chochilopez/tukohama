package com.gloit.tukohama.service;

import com.gloit.tukohama.entity.Layout;

import java.util.List;
import java.util.Optional;

public interface LayoutService {

    public Optional<Layout> findById(Long id);
    public List<Layout> findAll();

    public Optional<Layout> findByIdWithDeleted(Long id);
    public List<Layout> findAllWithDeleted();

    public Layout insert(Layout obj) throws Exception;
    public Layout update(Layout obj);
    public void delete(Long id);
    public Layout recycle(Layout obj);
    public void destroy(Long id);
}
