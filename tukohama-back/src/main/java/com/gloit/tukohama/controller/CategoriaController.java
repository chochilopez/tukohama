package com.gloit.tukohama.controller;

import com.gloit.tukohama.controller.form.CategoriaForm;
import com.gloit.tukohama.entity.Categoria;
import com.gloit.tukohama.service.implementation.CategoriaServiceImpl;
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
@RequestMapping(value = "/api/categoria")
public class CategoriaController {

    @Autowired
    CategoriaServiceImpl service;

    @GetMapping(value = "/todas")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Categoria>> getAll() {
        List<Categoria> categoria = service.findAll();
        if (categoria.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Categoria>>(categoria, HttpStatus.OK);
    }

    @GetMapping(value = "/todasConBorradas")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Categoria>> getAllWithDeleted() {
        List<Categoria> categoria = service.findAllWithDeleted();
        if (categoria.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Categoria>>(categoria, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorId/{id}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<Categoria> findById(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Categoria> categoria = service.findById(id);
        if (categoria.isPresent()) {
            return new ResponseEntity<Categoria>(categoria.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/buscarPorIdConBorradas/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Categoria> findByIdWithDeleted(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Categoria> categoria = service.findByIdWithDeleted(id);
        if (categoria.isPresent()) {
            return new ResponseEntity<Categoria>(categoria.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Categoria> insert(@Valid @RequestBody CategoriaForm categoriaForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(categoriaForm.formCategoriaDTO(new Categoria())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Categoria> update(@Valid @RequestBody CategoriaForm categoriaForm, @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Categoria> categoria = service.findById(id);
        if (!categoria.isPresent()){
            System.out.println("No se encuentra el Categoria que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(categoriaForm.formCategoriaDTO(categoria.get())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Categoria> categoria = service.findById(id);
        if (!categoria.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Categoria con ese id");
        service.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Categoria de id "+id+" ha sido eliminado correctamente");
    }

    @PostMapping(value = "/restaurar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> recycle(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Categoria> categoria = service.findByIdWithDeleted(id);
        if (!categoria.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Categoria con ese id");
        if (categoria.get().getBorrado()==null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El Categoria de id "+id+" no ha sido eliminado");
        service.recycle(categoria.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Categoria de id "+id+" ha sido recuperado correctamente");
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> destroy(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Categoria> categoria = service.findById(id);
        if (!categoria.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Categoria con ese id");
        service.destroy(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Categoria de id "+id+" ha sido destruido correctamente");
    }
}
