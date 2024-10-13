package com.gloit.tukohama.controller;

import com.gloit.tukohama.controller.form.ClasificadoForm;
import com.gloit.tukohama.entity.Clasificado;
import com.gloit.tukohama.service.implementation.ClasificadoServiceImpl;
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
@RequestMapping(value = "/api/clasificado")
public class ClasificadoController {

    @Autowired
    ClasificadoServiceImpl service;

    @GetMapping(value = "/todos")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Clasificado>> getAll() {
        List<Clasificado> clasificados= service.findAll();
        if (clasificados.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Clasificado>>(clasificados, HttpStatus.OK);
    }

    @GetMapping(value = "/todosConBorrados")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Clasificado>> getAllWithDeleted() {
        List<Clasificado> clasificados= service.findAllWithDeleted();
        if (clasificados.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Clasificado>>(clasificados, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorId/{id}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<Clasificado> findById(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Clasificado> clasificado = service.findById(id);
        if (clasificado.isPresent()) {
            return new ResponseEntity<Clasificado>(clasificado.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/buscarPorIdConBorrados/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Clasificado> findByIdWithDeleted(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Clasificado> clasificado = service.findByIdWithDeleted(id);
        if (clasificado.isPresent()) {
            return new ResponseEntity<Clasificado>(clasificado.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Clasificado> insert(@Valid @RequestBody ClasificadoForm clasificadoForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(clasificadoForm.formClasificadoDTO(new Clasificado())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Clasificado> update(@Valid @RequestBody ClasificadoForm clasificadoForm, @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Clasificado> clasificado = service.findById(id);
        if (!clasificado.isPresent()){
            System.out.println("No se encuentra el Clasificado que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(clasificadoForm.formClasificadoDTO(clasificado.get())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Clasificado> clasificado = service.findById(id);
        if (!clasificado.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Clasificado con ese id");
        service.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Clasificado de id "+id+" ha sido eliminado correctamente");
    }

    @PostMapping(value = "/restaurar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> recycle(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Clasificado> clasificado = service.findByIdWithDeleted(id);
        if (!clasificado.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Clasificado con ese id");
        if (clasificado.get().getBorrado()==null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El Clasificado de id "+id+" no ha sido eliminado");
        service.recycle(clasificado.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Clasificado de id "+id+" ha sido recuperado correctamente");
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> destroy(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Clasificado> clasificado = service.findById(id);
        if (!clasificado.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Clasificado con ese id");
        service.destroy(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Clasificado de id "+id+" ha sido destruido correctamente");
    }
}
