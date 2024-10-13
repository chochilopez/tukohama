package com.gloit.tukohama.controller;

import com.gloit.tukohama.controller.form.NecrologicaForm;
import com.gloit.tukohama.entity.Necrologica;
import com.gloit.tukohama.entity.Religion;
import com.gloit.tukohama.service.implementation.NecrologicaServiceImpl;
import com.gloit.tukohama.service.implementation.ReligionServiceImpl;
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
@RequestMapping(value = "/api/necrologica")
public class NecrologicaController {

    @Autowired
    NecrologicaServiceImpl service;

    @Autowired
    ReligionServiceImpl religionService;

    @GetMapping(value = "/todas")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Necrologica>> getAll() {
        List<Necrologica> necrologicas= service.findAll();
        if (necrologicas.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Necrologica>>(necrologicas, HttpStatus.OK);
    }

    @GetMapping(value = "/todasConBorradas")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Necrologica>> getAllWithDeleted() {
        List<Necrologica> necrologicas= service.findAllWithDeleted();
        if (necrologicas.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Necrologica>>(necrologicas, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorId/{id}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<Necrologica> findById(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Necrologica> necrologica = service.findById(id);
        if (necrologica.isPresent()) {
            return new ResponseEntity<Necrologica>(necrologica.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/buscarPorIdConBorradas/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Necrologica> findByIdWithDeleted(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Necrologica> necrologica = service.findByIdWithDeleted(id);
        if (necrologica.isPresent()) {
            return new ResponseEntity<Necrologica>(necrologica.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Necrologica> insert(@Valid @RequestBody NecrologicaForm necrologicaForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        Necrologica necrologica=new Necrologica();
        if (necrologicaForm.getIdReligion()!=null){
            Optional<Religion> religion=religionService.findById(necrologicaForm.getIdReligion());
            if (religion.isPresent())
                necrologica.setReligion(religion.get());
        }

        Necrologica respuesta=service.insert(necrologicaForm.formNecrologicaDTO(necrologica));

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Necrologica> update(@Valid @RequestBody NecrologicaForm necrologicaForm, @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Necrologica> necrologica = service.findById(id);
        if (!necrologica.isPresent()){
            System.out.println("No se encuentra el Necrologica que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        if (necrologicaForm.getIdReligion()!=null){
            Optional<Religion> religion=religionService.findById(necrologicaForm.getIdReligion());
            if (religion.isPresent())
                necrologica.get().setReligion(religion.get());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(necrologicaForm.formNecrologicaDTO(necrologica.get())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Necrologica> necrologica = service.findById(id);
        if (!necrologica.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Necrologica con ese id");
        service.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Necrologica de id "+id+" ha sido eliminado correctamente");
    }

    @PostMapping(value = "/restaurar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> recycle(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Necrologica> necrologica = service.findByIdWithDeleted(id);
        if (!necrologica.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Necrologica con ese id");
        if (necrologica.get().getBorrado()==null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El Necrologica de id "+id+" no ha sido eliminado");
        service.recycle(necrologica.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Necrologica de id "+id+" ha sido recuperado correctamente");
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> destroy(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Necrologica> necrologica = service.findById(id);
        if (!necrologica.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Necrologica con ese id");
        service.destroy(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Necrologica de id "+id+" ha sido destruido correctamente");
    }
}
