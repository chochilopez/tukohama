package com.gloit.tukohama.service.implementation;

import com.gloit.tukohama.entity.Multimedia;
import com.gloit.tukohama.entity.enumerator.EnumTipoArchivo;
import com.gloit.tukohama.helper.Helper;
import com.gloit.tukohama.repository.MultimediaRepository;
import com.gloit.tukohama.service.MultimediaService;
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
import java.util.Locale;
import java.util.Optional;

@Service
public class MultimediaServiceImpl implements MultimediaService {

    @Autowired
    private MultimediaRepository repositorio;

    private Authentication authentication;

    private String pathMultimedia;

    @Value("${tukohama.app.pathMultimedia}")
    public void setPathMultimedia(String pathMultimedia) {
        this.pathMultimedia = pathMultimedia;
    }

    @Override
    public Optional<Multimedia> findById(Long id){
        Optional<Multimedia> result=repositorio.findById(id);
        return result;
    };

    @Override
    public List<Multimedia> findAll(){
        List<Multimedia> result = repositorio.findAll();
        if (result.isEmpty())
            System.out.println("La tabla Imagen esta vacia, pero puede tener elementos reciclables.  ");
        return result;
    };

    @Override
    public Optional<Multimedia> findByIdWithDeleted(Long id){
        Optional<Multimedia> result=repositorio.findByIdWithDeleted(id);
        return result;
    };

    @Override
    public List<Multimedia> findAllWithDeleted(){
        List<Multimedia> result = repositorio.findAllWithDeleted();
        if (result.isEmpty())
            System.out.println("La tabla Imagen esta vacia.");
        return result;
    };

    @Override
    public Multimedia insert(Multimedia obj){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setCreado(Helper.getToday());
        obj.setCreador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public Multimedia update(Multimedia obj) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setModificado(Helper.getToday());
        obj.setModificador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public void delete(Long id){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Multimedia> obj=repositorio.findById(id);
        obj.get().setBorrado(Helper.getToday());
        obj.get().setBorrador(authentication.getName());
        repositorio.save(obj.get());
    };

    @Override
    public Multimedia recycle(Multimedia obj){
        obj.setBorrado(null);
        obj.setBorrador(null);
        obj=repositorio.save(obj);
        return obj;
    };

    @Override
    public void destroy(Long id){
        repositorio.deleteById(id);
    };

    @Override
    public List<Multimedia> buscarMultimedia(Integer tipo){
        List<Multimedia> result = repositorio.buscarMultimedia(tipo);
        if (result.isEmpty())
            System.out.println("La tabla Multimedia esta vacia para la consulta realizada.");
        return result;
    };

    @Override
    public Multimedia guardarMultimedia(
      MultipartFile multipartFile,
      Integer tipo,
      String nombre,
      String descripcion,
      String tamanio) throws Exception{
        String folder="";
        switch (tipo){
            case 0:
                folder=folder+"audio/";
                break;
            case 1:
                folder=folder+"image/";
                break;
            case 2:
                folder=folder+"video/";
                break;
            default:
                break;
        }

        folder =folder+Helper.getAnio()+"/";
        File tmpFile = new File(pathMultimedia+folder);
        if (!tmpFile.exists())
            tmpFile.mkdir();
        folder=folder+Helper.getMes()+"/";
        File tmpfile2= new File(pathMultimedia+folder);
        if (!tmpfile2.exists())
            tmpfile2.mkdir();
        folder=folder+Helper.getDia()+"/";
        File tmpfile3= new File(pathMultimedia+folder);
        if (!tmpfile3.exists())
            tmpfile3.mkdir();

        String nombreArchivo=nombre.toLowerCase().replace(" ","");
        folder=folder+nombreArchivo;

        File archivo=new File(pathMultimedia+folder);
        Multimedia multimedia=new Multimedia();

        if (archivo.exists())
            return multimedia;

        authentication = SecurityContextHolder.getContext().getAuthentication();
        multimedia.setNombreArchivo(nombreArchivo);
        multimedia.setTamanio(tamanio);
        multimedia.setDescripcion(descripcion);
        multimedia.setTipo(tipo);
        multimedia.setCreado(Helper.getToday());
        multimedia.setCreador(authentication.getName());
        byte[] bytes=multipartFile.getBytes();
        Path path=Paths.get(pathMultimedia+folder);
        Files.write(path , bytes);
        multimedia.setPath(folder);

        return repositorio.save(multimedia);
    };
}

