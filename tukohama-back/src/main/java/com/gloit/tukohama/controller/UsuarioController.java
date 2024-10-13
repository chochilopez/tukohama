package com.gloit.tukohama.controller;

import com.gloit.tukohama.controller.form.UsuarioForm;
import com.gloit.tukohama.entity.Usuario;
import com.gloit.tukohama.service.implementation.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioServiceImpl service;

    @GetMapping(value = "/buscarPorNombreUsuario/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Usuario> findByUsername(@PathVariable(name = "username") @javax.validation.constraints.Size(min = 1, max = 50) String username) {
        Optional<Usuario> usuario = service.findByUsername(username);
        if (usuario.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorEmail/{email}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Usuario> findByEmail(@PathVariable(name = "email") @javax.validation.constraints.Size(min = 1, max = 50) String email) {
        Optional<Usuario> usuario = service.findByEmail(email);
        if (usuario.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorNombre/{nombre}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Usuario>> findByName(@PathVariable(name = "nombre") @javax.validation.constraints.Size(min = 1, max = 50) String nombre) {
        List<Usuario> usuarios = service.findByName(nombre);
        if (usuarios.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorApellido/{apellido}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Usuario>>  findByLastname(@PathVariable(name = "apellido") @javax.validation.constraints.Size(min = 1, max = 50) String apellido) {
        List<Usuario> usuarios = service.findByLastname(apellido);
        if (usuarios.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    @GetMapping(value = "/todos")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> usuario= service.findAll();
        if (usuario.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }

    @GetMapping(value = "/todosConBorrados")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Usuario>> getAllWithDeleted() {
        List<Usuario> usuario= service.findAllWithDeleted();
        if (usuario.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorId/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Usuario> findById(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Usuario> usuario = service.findById(id);
        if (usuario.isPresent()) {
            return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/buscarPorIdConBorrados/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Usuario> findByIdWithDeleted(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Usuario> usuario = service.findByIdWithDeleted(id);
        if (usuario.isPresent()) {
            return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Usuario> insert(@Valid @RequestBody UsuarioForm usuarioForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(usuarioForm.formUsuarioDTO(new Usuario())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Usuario> update(@Valid @RequestBody UsuarioForm usuarioForm, @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Usuario> usuario = service.findById(id);
        if (!usuario.isPresent()){
            System.out.println("No se encuentra el Usuario que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(usuarioForm.formUsuarioDTO(usuario.get())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Usuario> usuario = service.findById(id);
        if (!usuario.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Usuario con ese id");
        service.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Usuario de id "+id+" ha sido eliminado correctamente");
    }

    @PostMapping(value = "/restaurar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> recycle(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Usuario> usuario = service.findByIdWithDeleted(id);
        if (!usuario.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Usuario con ese id");
        if (usuario.get().getBorrado()==null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El Usuario de id "+id+" no ha sido eliminado");
        service.recycle(usuario.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Usuario de id "+id+" ha sido recuperado correctamente");
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> destroy(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Usuario> usuario = service.findById(id);
        if (!usuario.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Usuario con ese id");
        service.destroy(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Usuario de id "+id+" ha sido destruido correctamente");
    }
}
