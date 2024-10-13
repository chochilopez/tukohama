package com.gloit.tukohama.service.implementation;

import com.gloit.tukohama.entity.Usuario;
import com.gloit.tukohama.helper.Helper;
import com.gloit.tukohama.repository.UsuarioRepository;
import com.gloit.tukohama.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repositorio;

    private Authentication authentication;

    @Override
    public Optional<Usuario> findByUsername(String username){
        Optional<Usuario> result = repositorio.findByUsername(username);
        if (result.isEmpty())
            System.out.println("Resultado vacio para la consulta username ->"+username+"<- en la tabla Usuario.");
        return result;
    };

    @Override
    public Optional<Usuario> findByEmail(String email){
        Optional<Usuario> result = repositorio.findByEmail(email);
        if (result.isEmpty())
            System.out.println("Resultado vacio para la consulta email ->"+email+"<- en la tabla Usuario.");
        return result;
    };

    @Override
    public List<Usuario> findByName(String name){
        List<Usuario> result = repositorio.findByName(name);
        if (result.isEmpty())
            System.out.println("Resultado vacio para la consulta nombre ->"+name+"<- en la tabla Usuario.");
        return result;
    };

    @Override
    public List<Usuario> findByLastname(String lastname){
        List<Usuario> result = repositorio.findByLastname(lastname);
        if (result.isEmpty())
            System.out.println("Resultado vacio para la consulta lastname ->"+lastname+"<- en la tabla Usuario.");
        return result;
    };

    @Override
    public Optional<Usuario> findById(Long id){
        Optional<Usuario> result=repositorio.findById(id);
        return result;
    };

    @Override
    public List<Usuario> findAll(){
        List<Usuario> result = repositorio.findAll();
        if (result.isEmpty())
            System.out.println("La tabla Usuario esta vacia, pero puede tener elementos reciclables.  ");
        return result;
    };

    @Override
    public Optional<Usuario> findByIdWithDeleted(Long id){
        Optional<Usuario> result=repositorio.findByIdWithDeleted(id);
        return result;
    };

    @Override
    public List<Usuario> findAllWithDeleted(){
        List<Usuario> result = repositorio.findAllWithDeleted();
        if (result.isEmpty())
            System.out.println("La tabla Usuario esta vacia.");
        return result;
    };

    @Override
    public Usuario insert(Usuario obj){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setCreado(Helper.getToday());
        obj.setCreador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public Usuario update(Usuario obj) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setModificado(Helper.getToday());
        obj.setModificador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public void delete(Long id){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Usuario> obj=repositorio.findById(id);
        obj.get().setBorrado(Helper.getToday());
        obj.get().setBorrador(authentication.getName());
        repositorio.save(obj.get());
    };

    @Override
    public Usuario recycle(Usuario obj){
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

