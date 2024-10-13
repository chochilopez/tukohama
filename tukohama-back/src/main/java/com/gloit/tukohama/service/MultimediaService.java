package com.gloit.tukohama.service;

import com.gloit.tukohama.entity.Multimedia;
import com.gloit.tukohama.entity.enumerator.EnumTipoArchivo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface MultimediaService {

    public Multimedia guardarMultimedia(
      MultipartFile multipartFile,
      Integer tipo,
      String nombre,
      String descripcion,
      String tamanio
    ) throws Exception;
    public List<Multimedia> buscarMultimedia(Integer tipo);

    public Optional<Multimedia> findById(Long id);
    public List<Multimedia> findAll();

    public Optional<Multimedia> findByIdWithDeleted(Long id);
    public List<Multimedia> findAllWithDeleted();

    public Multimedia insert(Multimedia obj) throws Exception;
    public Multimedia recycle(Multimedia obj);
    public void delete(Long id);
    public Multimedia update(Multimedia obj);
    public void destroy(Long id);
}
