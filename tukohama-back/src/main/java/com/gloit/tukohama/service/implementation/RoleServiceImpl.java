package com.gloit.tukohama.service.implementation;

import com.gloit.tukohama.entity.Role;
import com.gloit.tukohama.entity.enumerator.EnumRole;
import com.gloit.tukohama.helper.Helper;
import com.gloit.tukohama.repository.RoleRepository;
import com.gloit.tukohama.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repositorio;

    private Authentication authentication;

    @Override
    public Optional<Role> findById(Long id){
        Optional<Role> result=repositorio.findById(id);
        return result;
    };

    @Override
    public List<Role> findAll(){
        List<Role> result = repositorio.findAll();
        if (result.isEmpty())
            System.out.println("La tabla Role esta vacia, pero puede tener elementos reciclables.  ");
        return result;
    };

    @Override
    public Optional<Role> findByIdWithDeleted(Long id){
        Optional<Role> result=repositorio.findByIdWithDeleted(id);
        return result;
    };

    @Override
    public List<Role> findAllWithDeleted(){
        List<Role> result = repositorio.findAllWithDeleted();
        if (result.isEmpty())
            System.out.println("La tabla Role esta vacia.");
        return result;
    };

    @Override
    public Role insert(Role obj){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setCreado(Helper.getToday());
        obj.setCreador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public Role update(Role obj) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setModificado(Helper.getToday());
        obj.setModificador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public void delete(Long id){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Role> obj=repositorio.findById(id);
        obj.get().setBorrado(Helper.getToday());
        obj.get().setBorrador(authentication.getName());
        repositorio.save(obj.get());
    };

    @Override
    public Role recycle(Role obj){
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

