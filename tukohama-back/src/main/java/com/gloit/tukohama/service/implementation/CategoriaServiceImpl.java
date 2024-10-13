package com.gloit.tukohama.service.implementation;

import com.gloit.tukohama.entity.Categoria;
import com.gloit.tukohama.helper.Helper;
import com.gloit.tukohama.repository.CategoriaRepository;
import com.gloit.tukohama.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository repositorio;

    private Authentication authentication;

    @Override
    public Optional<Categoria> findById(Long id){
        Optional<Categoria> result=repositorio.findById(id);
        return result;
    };

    @Override
    public List<Categoria> findAll(){
        List<Categoria> result = repositorio.findAll();
        if (result.isEmpty())
            System.out.println("La tabla Categoria esta vacia, pero puede tener elementos reciclables.  ");
        return result;
    };

    @Override
    public Optional<Categoria> findByIdWithDeleted(Long id){
        Optional<Categoria> result=repositorio.findByIdWithDeleted(id);
        return result;
    };

    @Override
    public List<Categoria> findAllWithDeleted(){
        List<Categoria> result = repositorio.findAllWithDeleted();
        if (result.isEmpty())
            System.out.println("La tabla Categoria esta vacia.");
        return result;
    };

    @Override
    public Categoria insert(Categoria obj){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setCreado(Helper.getToday());
        obj.setCreador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public Categoria update(Categoria obj) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setModificado(Helper.getToday());
        obj.setModificador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public void delete(Long id){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Categoria> obj=repositorio.findById(id);
        obj.get().setBorrado(Helper.getToday());
        obj.get().setBorrador(authentication.getName());
        repositorio.save(obj.get());
    };

    @Override
    public Categoria recycle(Categoria obj){
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

