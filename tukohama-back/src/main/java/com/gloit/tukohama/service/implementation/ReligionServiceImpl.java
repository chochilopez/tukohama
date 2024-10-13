package com.gloit.tukohama.service.implementation;

import com.gloit.tukohama.entity.Religion;
import com.gloit.tukohama.helper.Helper;
import com.gloit.tukohama.repository.ReligionRepository;
import com.gloit.tukohama.service.ReligionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReligionServiceImpl implements ReligionService {

    @Autowired
    private ReligionRepository repositorio;

    private Authentication authentication;

    @Override
    public Optional<Religion> findById(Long id){
        Optional<Religion> result=repositorio.findById(id);
        return result;
    };

    @Override
    public List<Religion> findAll(){
        List<Religion> result = repositorio.findAll();
        if (result.isEmpty())
            System.out.println("La tabla Religion esta vacia, pero puede tener elementos reciclables.  ");
        return result;
    };

    @Override
    public Optional<Religion> findByIdWithDeleted(Long id){
        Optional<Religion> result=repositorio.findByIdWithDeleted(id);
        return result;
    };

    @Override
    public List<Religion> findAllWithDeleted(){
        List<Religion> result = repositorio.findAllWithDeleted();
        if (result.isEmpty())
            System.out.println("La tabla Religion esta vacia.");
        return result;
    };

    @Override
    public Religion insert(Religion obj){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setCreado(Helper.getToday());
        obj.setCreador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public Religion update(Religion obj) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setModificado(Helper.getToday());
        obj.setModificador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public void delete(Long id){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Religion> obj=repositorio.findById(id);
        obj.get().setBorrado(Helper.getToday());
        obj.get().setBorrador(authentication.getName());
        repositorio.save(obj.get());
    };

    @Override
    public Religion recycle(Religion obj){
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

