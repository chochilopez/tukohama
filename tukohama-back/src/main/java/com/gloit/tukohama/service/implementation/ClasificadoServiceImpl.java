package com.gloit.tukohama.service.implementation;

import com.gloit.tukohama.entity.Clasificado;
import com.gloit.tukohama.helper.Helper;
import com.gloit.tukohama.repository.ClasificadoRepository;
import com.gloit.tukohama.service.ClasificadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClasificadoServiceImpl implements ClasificadoService {

    @Autowired
    private ClasificadoRepository repositorio;

    private Authentication authentication;

    @Override
    public Optional<Clasificado> findById(Long id){
        Optional<Clasificado> result=repositorio.findById(id);
        return result;
    };

    @Override
    public List<Clasificado> findAll(){
        List<Clasificado> result = repositorio.findAll();
        if (result.isEmpty())
            System.out.println("La tabla Clasificado esta vacia, pero puede tener elementos reciclables.  ");
        return result;
    };

    @Override
    public Optional<Clasificado> findByIdWithDeleted(Long id){
        Optional<Clasificado> result=repositorio.findByIdWithDeleted(id);
        return result;
    };

    @Override
    public List<Clasificado> findAllWithDeleted(){
        List<Clasificado> result = repositorio.findAllWithDeleted();
        if (result.isEmpty())
            System.out.println("La tabla Baner esta vacia.");
        return result;
    };

    @Override
    public Clasificado insert(Clasificado obj){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setCreado(Helper.getToday());
        obj.setCreador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public Clasificado update(Clasificado obj) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setModificado(Helper.getToday());
        obj.setModificador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public void delete(Long id){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Clasificado> obj=repositorio.findById(id);
        obj.get().setBorrado(Helper.getToday());
        obj.get().setBorrador(authentication.getName());
        repositorio.save(obj.get());
    };

    @Override
    public Clasificado recycle(Clasificado obj){
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

