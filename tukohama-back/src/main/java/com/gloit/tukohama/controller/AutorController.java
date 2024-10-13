package com.gloit.tukohama.controller;

import com.gloit.tukohama.controller.form.AutorForm;
import com.gloit.tukohama.entity.Autor;
import com.gloit.tukohama.service.implementation.AutorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/autor")
public class AutorController {

    @Autowired
    AutorServiceImpl service;

    @GetMapping(value = "/todos")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Autor>> getAll() {
        List<Autor> autors= service.findAll();
        if (autors.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Autor>>(autors, HttpStatus.OK);
    }

    @GetMapping(value = "/todosConBorrados")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Autor>> getAllWithDeleted() {
        List<Autor> autors = service.findAll();
        if (autors.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Autor>>(autors, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorId/{id}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<Autor> findById(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Autor> autor = service.findById(id);
        if (autor.isPresent()) {
            return new ResponseEntity<Autor>(autor.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/buscarPorIdConBorrados/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Autor> findByIdWithDeleted(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Autor> autor = service.findByIdWithDeleted(id);
        if (autor.isPresent()) {
            return new ResponseEntity<Autor>(autor.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Autor> insert(@Valid @RequestBody AutorForm autorForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(autorForm.formAutorDTO(new Autor())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Autor> update(@Valid @RequestBody AutorForm autorForm, @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Autor> autor = service.findById(id);
        if (!autor.isPresent()){
            System.out.println("No se encuentra el Categoria que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(autorForm.formAutorDTO(autor.get())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Autor> autor = service.findById(id);
        if (!autor.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Autor con ese id");
        service.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Autor de id "+id+" ha sido eliminado correctamente");
    }

    @PostMapping(value = "/restaurar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> recycle(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Autor> autor = service.findByIdWithDeleted(id);
        if (!autor.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Autor con ese id");
        if (autor.get().getBorrado()==null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El Autor de id "+id+" no ha sido eliminado");
        service.recycle(autor.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Autor de id "+id+" ha sido recuperado correctamente");
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> destroy(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Autor> autor = service.findById(id);
        if (!autor.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Autor con ese id");
        service.destroy(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Autor de id "+id+" ha sido destruido correctamente");
    }
}
