package com.gloit.tukohama.service;

import com.gloit.tukohama.entity.WidgetClima;

import java.util.List;
import java.util.Optional;

public interface WidgetClimaService {

    public Optional<WidgetClima> findById(Long id);
    public List<WidgetClima> findAll();

    public Optional<WidgetClima> findByIdWithDeleted(Long id);
    public List<WidgetClima> findAllWithDeleted();

    public WidgetClima insert(WidgetClima obj) throws Exception;
    public WidgetClima update(WidgetClima obj);
    public void delete(Long id);
    public WidgetClima recycle(WidgetClima obj);
    public void destroy(Long id);
}
