package com.gloit.tukohama.service.implementation;

import com.gloit.tukohama.entity.Visita;
import com.gloit.tukohama.helper.Helper;
import com.gloit.tukohama.repository.VisitaRepository;
import com.gloit.tukohama.service.VisitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitaServiceImpl implements VisitaService {

    @Autowired
    private VisitaRepository repositorio;

    private Authentication authentication;

    @Override
    public Long cantidad(){
        return repositorio.count();
    }

    @Override
    public Optional<Visita> buscarPorIp(String ip){
        Optional<Visita> result=repositorio.buscarPorIp(ip);
        return result;
    };

    @Override
    public Optional<Visita> findById(Long id){
        Optional<Visita> result=repositorio.findById(id);
        return result;
    };

    @Override
    public List<Visita> findAll(){
        List<Visita> result = repositorio.findAll();
        if (result.isEmpty())
            System.out.println("La tabla Visita esta vacia, pero puede tener elementos reciclables.  ");
        return result;
    };

    @Override
    public Optional<Visita> findByIdWithDeleted(Long id){
        Optional<Visita> result=repositorio.findByIdWithDeleted(id);
        return result;
    };

    @Override
    public List<Visita> findAllWithDeleted(){
        List<Visita> result = repositorio.findAllWithDeleted();
        if (result.isEmpty())
            System.out.println("La tabla Visita esta vacia.");
        return result;
    };

    @Override
    public Visita insert(Visita obj){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setCreado(Helper.getToday());
        obj.setCreador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public Visita update(Visita obj) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setModificado(Helper.getToday());
        obj.setModificador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public void delete(Long id){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Visita> obj=repositorio.findById(id);
        obj.get().setBorrado(Helper.getToday());
        obj.get().setBorrador(authentication.getName());
        repositorio.save(obj.get());
    };

    @Override
    public Visita recycle(Visita obj){
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

