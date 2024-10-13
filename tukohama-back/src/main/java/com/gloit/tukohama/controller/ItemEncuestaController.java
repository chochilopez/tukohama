package com.gloit.tukohama.controller;

import com.gloit.tukohama.controller.form.ItemEncuestaForm;
import com.gloit.tukohama.entity.ItemEncuesta;
import com.gloit.tukohama.service.implementation.ItemEncuestaServiceImpl;
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
@RequestMapping(value = "/api/item_encuesta")
public class ItemEncuestaController {

    @Autowired
    ItemEncuestaServiceImpl service;

    @GetMapping(value = "/todos")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<ItemEncuesta>> getAll() {
        List<ItemEncuesta> encuestas= service.findAll();
        if (encuestas.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<ItemEncuesta>>(encuestas, HttpStatus.OK);
    }

    @GetMapping(value = "/todosConBorrados")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ItemEncuesta>> getAllWithDeleteds() {
        List<ItemEncuesta> encuestas= service.findAllWithDeleted();
        if (encuestas.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<ItemEncuesta>>(encuestas, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorId/{id}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<ItemEncuesta> findById(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<ItemEncuesta> itemEncuesta = service.findById(id);
        if (itemEncuesta.isPresent()) {
            return new ResponseEntity<ItemEncuesta>(itemEncuesta.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/buscarPorIdConBorrados/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ItemEncuesta> findByIdWithDeleted(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<ItemEncuesta> itemEncuesta = service.findByIdWithDeleted(id);
        if (itemEncuesta.isPresent()) {
            return new ResponseEntity<ItemEncuesta>(itemEncuesta.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<ItemEncuesta> insert(@Valid @RequestBody ItemEncuestaForm itemEncuestaForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(itemEncuestaForm.formItemEncuestaDTO(new ItemEncuesta())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<ItemEncuesta> update(@Valid @RequestBody ItemEncuestaForm itemEncuestaForm, @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<ItemEncuesta> itemEncuesta = service.findById(id);
        if (!itemEncuesta.isPresent()){
            System.out.println("No se encuentra el ItemEncuesta que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(itemEncuestaForm.formItemEncuestaDTO(itemEncuesta.get())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<ItemEncuesta> itemEncuesta = service.findById(id);
        if (!itemEncuesta.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un ItemEncuesta con ese id");
        service.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El ItemEncuesta de id "+id+" ha sido eliminado correctamente");
    }

    @PostMapping(value = "/restaurar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> recycle(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<ItemEncuesta> itemEncuesta = service.findByIdWithDeleted(id);
        if (!itemEncuesta.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un ItemEncuesta con ese id");
        if (itemEncuesta.get().getBorrado()==null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El ItemEncuesta de id "+id+" no ha sido eliminado");
        service.recycle(itemEncuesta.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El ItemEncuesta de id "+id+" ha sido recuperado correctamente");
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> destroy(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<ItemEncuesta> itemEncuesta = service.findById(id);
        if (!itemEncuesta.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un ItemEncuesta con ese id");
        service.destroy(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El ItemEncuesta de id "+id+" ha sido destruido correctamente");
    }
}
