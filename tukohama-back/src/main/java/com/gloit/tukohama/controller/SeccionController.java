package com.gloit.tukohama.controller;

import com.gloit.tukohama.controller.form.SeccionForm;
import com.gloit.tukohama.entity.Seccion;
import com.gloit.tukohama.service.implementation.SeccionServiceImpl;
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
@RequestMapping(value = "/api/seccion")
public class SeccionController {

    @Autowired
    SeccionServiceImpl service;

    @GetMapping(value = "/todas")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Seccion>> getAll() {
        List<Seccion> seccion= service.findAll();
        if (seccion.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Seccion>>(seccion, HttpStatus.OK);
    }

    @GetMapping(value = "/todasConBorradas")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Seccion>> getAllWithDeleted() {
        List<Seccion> seccion= service.findAllWithDeleted();
        if (seccion.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Seccion>>(seccion, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorId/{id}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<Seccion> findById(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Seccion> seccion = service.findById(id);
        if (seccion.isPresent()) {
            return new ResponseEntity<Seccion>(seccion.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/buscarPorIdConBorradas/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Seccion> findByIdWithDeleted(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Seccion> seccion = service.findByIdWithDeleted(id);
        if (seccion.isPresent()) {
            return new ResponseEntity<Seccion>(seccion.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Seccion> insert(@Valid @RequestBody SeccionForm seccionForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(seccionForm.formSeccionDTO(new Seccion())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Seccion> update(@Valid @RequestBody SeccionForm seccionForm, @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Seccion> seccion = service.findById(id);
        if (!seccion.isPresent()){
            System.out.println("No se encuentra el Seccion que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(seccionForm.formSeccionDTO(seccion.get())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Seccion> seccion = service.findById(id);
        if (!seccion.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Seccion con ese id");
        service.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Categoria de id "+id+" ha sido eliminado correctamente");
    }

    @PostMapping(value = "/restaurar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> recycle(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Seccion> seccion = service.findByIdWithDeleted(id);
        if (!seccion.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Seccion con ese id");
        if (seccion.get().getBorrado()==null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El Seccion de id "+id+" no ha sido eliminado");
        service.recycle(seccion.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Seccion de id "+id+" ha sido recuperado correctamente");
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> destroy(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Seccion> seccion = service.findById(id);
        if (!seccion.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Seccion con ese id");
        service.destroy(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Seccion de id "+id+" ha sido destruido correctamente");
    }
}
