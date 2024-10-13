package com.gloit.tukohama.service.implementation;

import com.gloit.tukohama.entity.Encuesta;
import com.gloit.tukohama.helper.Helper;
import com.gloit.tukohama.repository.EncuestaRepository;
import com.gloit.tukohama.service.EncuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EncuestaServiceImpl implements EncuestaService {

    @Autowired
    private EncuestaRepository repositorio;

    private Authentication authentication;

    @Override
    public Optional<Encuesta> findById(Long id){
        Optional<Encuesta> result=repositorio.findById(id);
        return result;
    };

    @Override
    public List<Encuesta> findAll(){
        List<Encuesta> result = repositorio.findAll();
        if (result.isEmpty())
            System.out.println("La tabla Encuesta esta vacia, pero puede tener elementos reciclables.  ");
        return result;
    };

    @Override
    public Optional<Encuesta> findByIdWithDeleted(Long id){
        Optional<Encuesta> result=repositorio.findByIdWithDeleted(id);
        return result;
    };

    @Override
    public List<Encuesta> findAllWithDeleted(){
        List<Encuesta> result = repositorio.findAllWithDeleted();
        if (result.isEmpty())
            System.out.println("La tabla Encuesta esta vacia.");
        return result;
    };

    @Override
    public Encuesta insert(Encuesta obj){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setCreado(Helper.getToday());
        obj.setCreador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public Encuesta update(Encuesta obj) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setModificado(Helper.getToday());
        obj.setModificador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public void delete(Long id){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Encuesta> obj=repositorio.findById(id);
        obj.get().setBorrado(Helper.getToday());
        obj.get().setBorrador(authentication.getName());
        repositorio.save(obj.get());
    };

    @Override
    public Encuesta recycle(Encuesta obj){
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

