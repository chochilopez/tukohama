package com.gloit.tukohama.controller;

import com.gloit.tukohama.controller.form.EncuestaForm;
import com.gloit.tukohama.entity.Encuesta;
import com.gloit.tukohama.service.implementation.EncuestaServiceImpl;
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
@RequestMapping(value = "/api/encuesta")
public class EncuestaController {

    @Autowired
    EncuestaServiceImpl service;

    @GetMapping(value = "/todas")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Encuesta>> getAll() {
        List<Encuesta> encuestas= service.findAll();
        if (encuestas.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Encuesta>>(encuestas, HttpStatus.OK);
    }

    @GetMapping(value = "/todasConBorradas")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Encuesta>> getAllWithDeleted() {
        List<Encuesta> encuestas= service.findAllWithDeleted();
        if (encuestas.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Encuesta>>(encuestas, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorId/{id}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<Encuesta> findById(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Encuesta> encuesta = service.findById(id);
        if (encuesta.isPresent()) {
            return new ResponseEntity<Encuesta>(encuesta.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/buscarPorIdConBoradas/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Encuesta> findByIdWithDeleted(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Encuesta> encuesta = service.findByIdWithDeleted(id);
        if (encuesta.isPresent()) {
            return new ResponseEntity<Encuesta>(encuesta.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Encuesta> insert(@Valid @RequestBody EncuestaForm encuestaForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(encuestaForm.formEncuestaDTO(new Encuesta())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Encuesta> update(@Valid @RequestBody EncuestaForm encuestaForm, @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Encuesta> encuesta = service.findById(id);
        if (!encuesta.isPresent()){
            System.out.println("No se encuentra el Encuesta que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(encuestaForm.formEncuestaDTO(encuesta.get())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Encuesta> encuesta = service.findById(id);
        if (!encuesta.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Encuesta con ese id");
        service.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Encuesta de id "+id+" ha sido eliminado correctamente");
    }

    @PostMapping(value = "/restaurar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> recycle(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Encuesta> encuesta = service.findByIdWithDeleted(id);
        if (!encuesta.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Encuesta con ese id");
        if (encuesta.get().getBorrado()==null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El Encuesta de id "+id+" no ha sido eliminado");
        service.recycle(encuesta.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Encuesta de id "+id+" ha sido recuperado correctamente");
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> destroy(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Encuesta> encuesta = service.findById(id);
        if (!encuesta.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Encuesta con ese id");
        service.destroy(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Encuesta de id "+id+" ha sido destruido correctamente");
    }
}
