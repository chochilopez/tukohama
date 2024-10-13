package com.gloit.tukohama.service.implementation;

import com.gloit.tukohama.entity.Seccion;
import com.gloit.tukohama.helper.Helper;
import com.gloit.tukohama.repository.SeccionRepository;
import com.gloit.tukohama.service.SeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeccionServiceImpl implements SeccionService {

    @Autowired
    private SeccionRepository repositorio;

    private Authentication authentication;

    @Override
    public Optional<Seccion> findById(Long id){
        Optional<Seccion> result=repositorio.findById(id);
        return result;
    };

    @Override
    public List<Seccion> findAll(){
        List<Seccion> result = repositorio.findAll();
        if (result.isEmpty())
            System.out.println("La tabla Seccion esta vacia, pero puede tener elementos reciclables.  ");
        return result;
    };

    @Override
    public Optional<Seccion> findByIdWithDeleted(Long id){
        Optional<Seccion> result=repositorio.findByIdWithDeleted(id);
        return result;
    };

    @Override
    public List<Seccion> findAllWithDeleted(){
        List<Seccion> result = repositorio.findAllWithDeleted();
        if (result.isEmpty())
            System.out.println("La tabla Seccion esta vacia.");
        return result;
    };

    @Override
    public Seccion insert(Seccion obj){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setCreado(Helper.getToday());
        obj.setCreador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public Seccion update(Seccion obj) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setModificado(Helper.getToday());
        obj.setModificador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public void delete(Long id){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Seccion> obj=repositorio.findById(id);
        obj.get().setBorrado(Helper.getToday());
        obj.get().setBorrador(authentication.getName());
        repositorio.save(obj.get());
    };

    @Override
    public Seccion recycle(Seccion obj){
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

