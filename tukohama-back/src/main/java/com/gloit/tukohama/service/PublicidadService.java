package com.gloit.tukohama.service;

import com.gloit.tukohama.entity.Multimedia;
import com.gloit.tukohama.entity.Publicidad;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PublicidadService {

    public Publicidad guardarPublicidad(MultipartFile multipartFile, Integer tipo, Integer importancia, String firma, String link) throws Exception;

    public Optional<Publicidad> findById(Long id);
    public List<Publicidad> findAll();

    public Optional<Publicidad> findByIdWithDeleted(Long id);
    public List<Publicidad> findAllWithDeleted();

    public Publicidad insert(Publicidad obj) throws Exception;
    public Publicidad update(Publicidad obj);
    public void delete(Long id);
    public Publicidad recycle(Publicidad obj);
    public void destroy(Long id);
}
