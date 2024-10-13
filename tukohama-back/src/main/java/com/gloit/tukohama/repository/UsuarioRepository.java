package com.gloit.tukohama.repository;

import com.gloit.tukohama.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select obj from Usuario as obj where obj.borrado is null and obj.id=:id")
    public Optional<Usuario> findById(@Param("id") Long id);
    @Query("select obj from Usuario as obj where obj.borrado is null ")
    public List<Usuario> findAll();


    @Query("select obj from Usuario as obj where obj.id=:id")
    public Optional<Usuario> findByIdWithDeleted(@Param("id") Long id);
    @Query("select obj from Usuario as obj")
    public List<Usuario> findAllWithDeleted();


    @Query("select obj from Usuario obj where obj.borrado is null and obj.username=:username")
    public Optional<Usuario> findByUsername(@Param("username")String username);
    @Query("select obj from Usuario obj where obj.borrado is null and obj.email=:email")
    public Optional<Usuario> findByEmail(@Param("email")String email);
    @Query("select obj from Usuario obj where obj.borrado is null and obj.nombre like %:name%")
    public List<Usuario> findByName(@Param("name")String name);
    @Query("select obj from Usuario obj where obj.borrado is null and obj.apellido like %:lastname%" )
    public List<Usuario> findByLastname(@Param("lastname")String lastname);
}

