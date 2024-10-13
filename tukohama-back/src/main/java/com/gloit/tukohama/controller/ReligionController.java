package com.gloit.tukohama.controller;

import com.gloit.tukohama.controller.form.ReligionForm;
import com.gloit.tukohama.entity.Religion;
import com.gloit.tukohama.service.implementation.ReligionServiceImpl;
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
@RequestMapping(value = "/api/religion")
public class ReligionController {

    @Autowired
    ReligionServiceImpl service;

    @GetMapping(value = "/todas")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Religion>> getAll() {
        List<Religion> religion= service.findAll();
        if (religion.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Religion>>(religion, HttpStatus.OK);
    }

    @GetMapping(value = "/todasConBorradas")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Religion>> getAllWithDeleted() {
        List<Religion> religion= service.findAllWithDeleted();
        if (religion.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Religion>>(religion, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorId/{id}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<Religion> findById(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Religion> religion = service.findById(id);
        if (religion.isPresent()) {
            return new ResponseEntity<Religion>(religion.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/buscarPorIdConBorradas/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Religion> findByIdWithDeleted(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Religion> religion = service.findByIdWithDeleted(id);
        if (religion.isPresent()) {
            return new ResponseEntity<Religion>(religion.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Religion> insert(@Valid @RequestBody ReligionForm religionForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(religionForm.formReligionDTO(new Religion())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Religion> update(@Valid @RequestBody ReligionForm religionForm, @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Religion> religion = service.findById(id);
        if (!religion.isPresent()){
            System.out.println("No se encuentra le Religion que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(religionForm.formReligionDTO(religion.get())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Religion> religion = service.findById(id);
        if (!religion.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe una Religion con ese id");
        service.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("La Religion de id "+id+" ha sido eliminado correctamente");
    }

    @PostMapping(value = "/restaurar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> recycle(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Religion> religion = service.findByIdWithDeleted(id);
        if (!religion.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe una Religion con ese id");
        if (religion.get().getBorrado()==null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La Religion de id "+id+" no ha sido eliminado");
        service.recycle(religion.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("La Religion de id "+id+" ha sido recuperado correctamente");
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> destroy(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Religion> religion = service.findById(id);
        if (!religion.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe una Religion con ese id");
        service.destroy(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Religion de id "+id+" ha sido destruido correctamente");
    }
}
