package com.gloit.tukohama.service.implementation;

import com.gloit.tukohama.entity.Layout;
import com.gloit.tukohama.helper.Helper;
import com.gloit.tukohama.repository.LayoutRepository;
import com.gloit.tukohama.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LayoutServiceImpl implements LayoutService {

    @Autowired
    private LayoutRepository repositorio;

    private Authentication authentication;

    @Override
    public Optional<Layout> findById(Long id){
        Optional<Layout> result=repositorio.findById(id);
        return result;
    };

    @Override
    public List<Layout> findAll(){
        List<Layout> result = repositorio.findAll();
        if (result.isEmpty())
            System.out.println("La tabla Layout esta vacia, pero puede tener elementos reciclables.  ");
        return result;
    };

    @Override
    public Optional<Layout> findByIdWithDeleted(Long id){
        Optional<Layout> result=repositorio.findByIdWithDeleted(id);
        return result;
    };

    @Override
    public List<Layout> findAllWithDeleted(){
        List<Layout> result = repositorio.findAllWithDeleted();
        if (result.isEmpty())
            System.out.println("La tabla Layout esta vacia.");
        return result;
    };

    @Override
    public Layout insert(Layout obj){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setCreado(Helper.getToday());
        obj.setCreador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public Layout update(Layout obj) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setModificado(Helper.getToday());
        obj.setModificador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public void delete(Long id){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Layout> obj=repositorio.findById(id);
        obj.get().setBorrado(Helper.getToday());
        obj.get().setBorrador(authentication.getName());
        repositorio.save(obj.get());
    };

    @Override
    public Layout recycle(Layout obj){
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

