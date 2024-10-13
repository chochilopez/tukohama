package com.gloit.tukohama.service.implementation;

import com.gloit.tukohama.entity.Necrologica;
import com.gloit.tukohama.helper.Helper;
import com.gloit.tukohama.repository.NecrologicaRepository;
import com.gloit.tukohama.service.NecrologicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NecrologicaServiceImpl implements NecrologicaService {

    @Autowired
    private NecrologicaRepository repositorio;

    private Authentication authentication;

    @Override
    public Optional<Necrologica> findById(Long id){
        Optional<Necrologica> result=repositorio.findById(id);
        return result;
    };

    @Override
    public List<Necrologica> findAll(){
        List<Necrologica> result = repositorio.findAll();
        if (result.isEmpty())
            System.out.println("La tabla Necrologica esta vacia, pero puede tener elementos reciclables.  ");
        return result;
    };

    @Override
    public Optional<Necrologica> findByIdWithDeleted(Long id){
        Optional<Necrologica> result=repositorio.findByIdWithDeleted(id);
        return result;
    };

    @Override
    public List<Necrologica> findAllWithDeleted(){
        List<Necrologica> result = repositorio.findAllWithDeleted();
        if (result.isEmpty())
            System.out.println("La tabla Necrologica esta vacia.");
        return result;
    };

    @Override
    public Necrologica insert(Necrologica obj){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setCreado(Helper.getToday());
        obj.setCreador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public Necrologica update(Necrologica obj) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setModificado(Helper.getToday());
        obj.setModificador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public void delete(Long id){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Necrologica> obj=repositorio.findById(id);
        obj.get().setBorrado(Helper.getToday());
        obj.get().setBorrador(authentication.getName());
        repositorio.save(obj.get());
    };

    @Override
    public Necrologica recycle(Necrologica obj){
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

