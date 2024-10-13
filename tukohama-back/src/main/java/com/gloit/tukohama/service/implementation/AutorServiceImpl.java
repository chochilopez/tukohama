package com.gloit.tukohama.service.implementation;

import com.gloit.tukohama.entity.Autor;
import com.gloit.tukohama.helper.Helper;
import com.gloit.tukohama.repository.AutorRepository;
import com.gloit.tukohama.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository repositorio;

    private Authentication authentication;

    @Override
    public Optional<Autor> findById(Long id){
        Optional<Autor> result=repositorio.findById(id);
        return result;
    };

    @Override
    public List<Autor> findAll(){
        List<Autor> result = repositorio.findAll();
        if (result.isEmpty())
            System.out.println("La tabla Autor esta vacia, pero puede tener elementos reciclables.  ");
        return result;
    };

    @Override
    public Optional<Autor> findByIdWithDeleted(Long id){
        Optional<Autor> result=repositorio.findByIdWithDeleted(id);
        return result;
    };

    @Override
    public List<Autor> findAllWithDeleted(){
        List<Autor> result = repositorio.findAllWithDeleted();
        if (result.isEmpty())
            System.out.println("La tabla Autor esta vacia.");
        return result;
    };

    @Override
    public Autor insert(Autor obj){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setCreado(Helper.getToday());
        obj.setCreador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public Autor update(Autor obj) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setModificado(Helper.getToday());
        obj.setModificador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public void delete(Long id){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Autor> obj=repositorio.findById(id);
        obj.get().setBorrado(Helper.getToday());
        obj.get().setBorrador(authentication.getName());
        repositorio.save(obj.get());
    };

    @Override
    public Autor recycle(Autor obj){
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

