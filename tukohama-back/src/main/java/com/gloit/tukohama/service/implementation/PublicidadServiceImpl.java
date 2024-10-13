package com.gloit.tukohama.service.implementation;

import com.gloit.tukohama.entity.Multimedia;
import com.gloit.tukohama.entity.Publicidad;
import com.gloit.tukohama.helper.Helper;
import com.gloit.tukohama.repository.PublicidadRepository;
import com.gloit.tukohama.service.PublicidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class PublicidadServiceImpl implements PublicidadService {

    @Autowired
    private PublicidadRepository repositorio;

    private Authentication authentication;

    private String pathPublicidades;

    @Value("${tukohama.app.pathPublicidades}")
    public void setPathPublicidades(String pathPublicidades) {
        this.pathPublicidades = pathPublicidades;
    }

    @Override
    public Optional<Publicidad> findById(Long id){
        Optional<Publicidad> result=repositorio.findById(id);
        return result;
    };

    @Override
    public List<Publicidad> findAll(){
        List<Publicidad> result = repositorio.findAll();
        if (result.isEmpty())
            System.out.println("La tabla Publicidad esta vacia, pero puede tener elementos reciclables.  ");
        return result;
    };

    @Override
    public Optional<Publicidad> findByIdWithDeleted(Long id){
        Optional<Publicidad> result=repositorio.findByIdWithDeleted(id);
        return result;
    };

    @Override
    public List<Publicidad> findAllWithDeleted(){
        List<Publicidad> result = repositorio.findAllWithDeleted();
        if (result.isEmpty())
            System.out.println("La tabla Publicidad esta vacia.");
        return result;
    };

    @Override
    public Publicidad guardarPublicidad(MultipartFile multipartFile, Integer tipo, Integer importancia, String firma, String link) throws Exception{
        String folder="";
        folder+=Helper.getAnio()+"/";
        File tmpFile = new File(pathPublicidades+folder);
        if (!tmpFile.exists())
            tmpFile.mkdir();
        folder+=Helper.getMes()+"/";
        File tmpfile2= new File(pathPublicidades+folder);
        if (!tmpfile2.exists())
            tmpfile2.mkdir();
        folder+=Helper.getDia()+"/";
        File tmpfile3= new File(pathPublicidades+folder);
        if (!tmpfile3.exists())
            tmpfile3.mkdir();

        //TODO se podria cambiar nombre del rchivo para evitar colisiones, lo mismo en multimedia
        String nombreArchivo=multipartFile.getOriginalFilename().toLowerCase().replace(" ","");
        folder+=nombreArchivo;

        File archivo=new File(pathPublicidades+folder);
        Publicidad publicidad=new Publicidad();

        if (archivo.exists())
            return publicidad;

        authentication = SecurityContextHolder.getContext().getAuthentication();
        publicidad.setNombreArchivo(nombreArchivo);
        publicidad.setTipo(tipo);
        publicidad.setImportancia(importancia);
        publicidad.setFirma(firma);
        publicidad.setLink(link);
        publicidad.setCreado(Helper.getToday());
        publicidad.setCreador(authentication.getName());
        byte[] bytes=multipartFile.getBytes();
        Path path= Paths.get(pathPublicidades+folder);
        Files.write(path , bytes);
        publicidad.setPath(folder);

        return repositorio.save(publicidad);
    };

    @Override
    public Publicidad insert(Publicidad obj){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setCreado(Helper.getToday());
        obj.setCreador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public Publicidad update(Publicidad obj) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setModificado(Helper.getToday());
        obj.setModificador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public void delete(Long id){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Publicidad> obj=repositorio.findById(id);
        obj.get().setBorrado(Helper.getToday());
        obj.get().setBorrador(authentication.getName());
        repositorio.save(obj.get());
    };

    @Override
    public Publicidad recycle(Publicidad obj){
        obj.setBorrado(null);
        obj.setBorrador(null);
        obj=repositorio.save(obj);
        return obj;
    };

    @Override
    public void destroy(Long id){
        repositorio.deleteById(id);
    };
}

