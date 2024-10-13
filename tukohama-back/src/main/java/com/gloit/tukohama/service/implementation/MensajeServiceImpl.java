package com.gloit.tukohama.service.implementation;

import com.gloit.tukohama.entity.Mensaje;
import com.gloit.tukohama.helper.Helper;
import com.gloit.tukohama.repository.MensajeRepository;
import com.gloit.tukohama.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensajeServiceImpl implements MensajeService {

    @Autowired
    private MensajeRepository repositorio;

    private Authentication authentication;

    @Override
    public Optional<Mensaje> findById(Long id){
        Optional<Mensaje> result=repositorio.findById(id);
        return result;
    };

    @Override
    public List<Mensaje> findAll(){
        List<Mensaje> result = repositorio.findAll();
        if (result.isEmpty())
            System.out.println("La tabla Mensaje esta vacia, pero puede tener elementos reciclables.  ");
        return result;
    };

    @Override
    public Optional<Mensaje> findByIdWithDeleted(Long id){
        Optional<Mensaje> result=repositorio.findByIdWithDeleted(id);
        return result;
    };

    @Override
    public List<Mensaje> findAllWithDeleted(){
        List<Mensaje> result = repositorio.findAllWithDeleted();
        if (result.isEmpty())
            System.out.println("La tabla Mensaje esta vacia.");
        return result;
    };

    @Override
    public Mensaje insert(Mensaje obj){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setCreado(Helper.getToday());
        obj.setCreador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public Mensaje update(Mensaje obj) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setModificado(Helper.getToday());
        obj.setModificador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public void delete(Long id){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Mensaje> obj=repositorio.findById(id);
        obj.get().setBorrado(Helper.getToday());
        obj.get().setBorrador(authentication.getName());
        repositorio.save(obj.get());
    };

    @Override
    public Mensaje recycle(Mensaje obj){
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

