package com.gloit.tukohama.service.implementation;

import com.gloit.tukohama.entity.WidgetClima;
import com.gloit.tukohama.helper.Helper;
import com.gloit.tukohama.repository.WidgetClimaRepository;
import com.gloit.tukohama.service.WidgetClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WidgetClimaServiceImpl implements WidgetClimaService {

    @Autowired
    private WidgetClimaRepository repositorio;

    private Authentication authentication;

    @Override
    public Optional<WidgetClima> findById(Long id){
        Optional<WidgetClima> result=repositorio.findById(id);
        return result;
    };

    @Override
    public List<WidgetClima> findAll(){
        List<WidgetClima> result = repositorio.findAll();
        if (result.isEmpty())
            System.out.println("La tabla WidgetClima esta vacia, pero puede tener elementos reciclables.  ");
        return result;
    };

    @Override
    public Optional<WidgetClima> findByIdWithDeleted(Long id){
        Optional<WidgetClima> result=repositorio.findByIdWithDeleted(id);
        return result;
    };

    @Override
    public List<WidgetClima> findAllWithDeleted(){
        List<WidgetClima> result = repositorio.findAllWithDeleted();
        if (result.isEmpty())
            System.out.println("La tabla WidgetClima esta vacia.");
        return result;
    };

    @Override
    public WidgetClima insert(WidgetClima obj){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setCreado(Helper.getToday());
        obj.setCreador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public WidgetClima update(WidgetClima obj) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setModificado(Helper.getToday());
        obj.setModificador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public void delete(Long id){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<WidgetClima> obj=repositorio.findById(id);
        obj.get().setBorrado(Helper.getToday());
        obj.get().setBorrador(authentication.getName());
        repositorio.save(obj.get());
    };

    @Override
    public WidgetClima recycle(WidgetClima obj){
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

