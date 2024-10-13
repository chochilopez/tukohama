package com.gloit.tukohama.service;

import com.gloit.tukohama.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public Optional<Usuario> findByUsername(String username);
    public Optional<Usuario> findByEmail(String email);
    public List<Usuario> findByName(String name);
    public List<Usuario> findByLastname(String lastname);

    public Optional<Usuario> findById(Long id);
    public List<Usuario> findAll();

    public Optional<Usuario> findByIdWithDeleted(Long id);
    public List<Usuario> findAllWithDeleted();

    public Usuario insert(Usuario obj) throws Exception;
    public Usuario update(Usuario obj);
    public void delete(Long id);
    public Usuario recycle(Usuario obj);
    public void destroy(Long id);
}
