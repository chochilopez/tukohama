package com.gloit.tukohama.controller;

import com.gloit.tukohama.controller.form.VisitaForm;
import com.gloit.tukohama.entity.Visita;
import com.gloit.tukohama.service.implementation.VisitaServiceImpl;
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
@RequestMapping(value = "/api/visita")
public class VisitaController {

    @Autowired
    VisitaServiceImpl service;

    @GetMapping(value = "/cantidad")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Long> cantidad() {
        Long cantidad= service.cantidad();
        if (cantidad == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<Long>(cantidad, HttpStatus.OK);
    }


    @GetMapping(value = "/buscarPorIp/{ip}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Visita> findByIp(@PathVariable(name = "ip") @javax.validation.constraints.Size(min = 1, max = 18) String ip) {
        Optional<Visita> visita = service.buscarPorIp(ip);
        if (visita.isPresent()) {
            return new ResponseEntity<Visita>(visita.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/todas")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<List<Visita>> getAll() {
        List<Visita> visitas= service.findAll();
        if (visitas.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Visita>>(visitas, HttpStatus.OK);
    }

    @GetMapping(value = "/todasConBorrados")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Visita>> getAllWithDeleted() {
        List<Visita> visitas= service.findAllWithDeleted();
        if (visitas.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Visita>>(visitas, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorId/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Visita> findById(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Visita> visita = service.findById(id);
        if (visita.isPresent()) {
            return new ResponseEntity<Visita>(visita.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/buscarPorIdConBorrados/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Visita> findByIdWithDeleted(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Visita> visita = service.findByIdWithDeleted(id);
        if (visita.isPresent()) {
            return new ResponseEntity<Visita>(visita.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<Visita> insert(@Valid @RequestBody VisitaForm visitaForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(visitaForm.formVisitaDTO(new Visita())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Visita> update(@Valid @RequestBody VisitaForm visitaForm, @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Visita> visita = service.findById(id);
        if (!visita.isPresent()){
            System.out.println("No se encuentra el Role que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(visitaForm.formVisitaDTO(visita.get())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Visita> visita = service.findById(id);
        if (!visita.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Role con ese id");
        service.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Role de id "+id+" ha sido eliminado correctamente");
    }

    @PostMapping(value = "/restaurar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> recycle(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Visita> visita = service.findByIdWithDeleted(id);
        if (!visita.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Role con ese id");
        if (visita.get().getBorrado()==null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El Role de id "+id+" no ha sido eliminado");
        service.recycle(visita.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Role de id "+id+" ha sido recuperado correctamente");
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> destroy(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Visita> visita = service.findById(id);
        if (!visita.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Role con ese id");
        service.destroy(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Role de id "+id+" ha sido destruido correctamente");
    }
}
