package com.gloit.tukohama.service.implementation;

import com.gloit.tukohama.entity.ItemEncuesta;
import com.gloit.tukohama.helper.Helper;
import com.gloit.tukohama.repository.ItemEncuestaRepository;
import com.gloit.tukohama.service.ItemEncuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ItemEncuestaServiceImpl implements ItemEncuestaService {

    @Autowired
    private ItemEncuestaRepository repositorio;

    private Authentication authentication;

    @Override
    public Optional<ItemEncuesta> findById(Long id){
        Optional<ItemEncuesta> result=repositorio.findById(id);
        return result;
    };

    @Override
    public List<ItemEncuesta> findAll(){
        List<ItemEncuesta> result = repositorio.findAll();
        if (result.isEmpty())
            System.out.println("La tabla ItemEncuesta esta vacia, pero puede tener elementos reciclables.  ");
        return result;
    };

    @Override
    public Optional<ItemEncuesta> findByIdWithDeleted(Long id){
        Optional<ItemEncuesta> result=repositorio.findByIdWithDeleted(id);
        return result;
    };

    @Override
    public List<ItemEncuesta> findAllWithDeleted(){
        List<ItemEncuesta> result = repositorio.findAllWithDeleted();
        if (result.isEmpty())
            System.out.println("La tabla ItemEncuesta esta vacia.");
        return result;
    };

    @Override
    public ItemEncuesta insert(ItemEncuesta obj){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setCreado(Helper.getToday());
        obj.setCreador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public ItemEncuesta update(ItemEncuesta obj) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setModificado(Helper.getToday());
        obj.setModificador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public void delete(Long id){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<ItemEncuesta> obj=repositorio.findById(id);
        obj.get().setBorrado(Helper.getToday());
        obj.get().setBorrador(authentication.getName());
        repositorio.save(obj.get());
    };

    @Override
    public ItemEncuesta recycle(ItemEncuesta obj){
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

