package com.gloit.tukohama.controller;

import com.gloit.tukohama.controller.form.LayoutForm;
import com.gloit.tukohama.entity.Layout;
import com.gloit.tukohama.service.implementation.LayoutServiceImpl;
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
@RequestMapping(value = "/api/layout")
public class LayoutController {

    @Autowired
    LayoutServiceImpl service;

    @GetMapping(value = "/todos")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Layout>> getAll() {
        List<Layout> layouts= service.findAll();
        if (layouts.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Layout>>(layouts, HttpStatus.OK);
    }

    @GetMapping(value = "/todosConBorrados")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Layout>> getAllWithDeleted() {
        List<Layout> layouts= service.findAllWithDeleted();
        if (layouts.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Layout>>(layouts, HttpStatus.OK);
    }


    @GetMapping(value = "/buscarPorId/{id}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<Layout> findById(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Layout> autor = service.findById(id);
        if (autor.isPresent()) {
            return new ResponseEntity<Layout>(autor.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/buscarPorIdConBorrados/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Layout> findByIdWithDeleted(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Layout> layout = service.findByIdWithDeleted(id);
        if (layout.isPresent()) {
            return new ResponseEntity<Layout>(layout.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Layout> insert(@Valid @RequestBody LayoutForm layoutForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(layoutForm.formLayoutDTO(new Layout())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Layout> update(@Valid @RequestBody LayoutForm layoutForm, @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Layout> layout = service.findById(id);
        if (!layout.isPresent()){
            System.out.println("No se encuentra el Layout que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(layoutForm.formLayoutDTO(layout.get())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Layout> layout = service.findById(id);
        if (!layout.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Layout con ese id");
        service.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Layout de id "+id+" ha sido eliminado correctamente");
    }

    @PostMapping(value = "/restaurar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> recycle(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Layout> layout = service.findByIdWithDeleted(id);
        if (!layout.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Layout con ese id");
        if (layout.get().getBorrado()==null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El Layout de id "+id+" no ha sido eliminado");
        service.recycle(layout.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Layout de id "+id+" ha sido recuperado correctamente");
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> destroy(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Layout> layout = service.findById(id);
        if (!layout.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Layout con ese id");
        service.destroy(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Layout de id "+id+" ha sido destruido correctamente");
    }
}
