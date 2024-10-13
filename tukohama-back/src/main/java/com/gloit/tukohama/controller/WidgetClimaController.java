package com.gloit.tukohama.controller;

import com.gloit.tukohama.controller.form.WidgetClimaForm;
import com.gloit.tukohama.entity.WidgetClima;
import com.gloit.tukohama.service.implementation.WidgetClimaServiceImpl;
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
@RequestMapping(value = "/api/widget_clima")
public class WidgetClimaController {

    @Autowired
    WidgetClimaServiceImpl service;

    @GetMapping(value = "/todos")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<WidgetClima>> getAll() {
        List<WidgetClima> widgetClimas= service.findAll();
        if (widgetClimas.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<WidgetClima>>(widgetClimas, HttpStatus.OK);
    }

    @GetMapping(value = "/todosConBorrados")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<WidgetClima>> getAllWithDeleted() {
        List<WidgetClima> widgetClimas= service.findAllWithDeleted();
        if (widgetClimas.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<WidgetClima>>(widgetClimas, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorId/{id}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<WidgetClima> findById(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<WidgetClima> widgetClima = service.findById(id);
        if (widgetClima.isPresent()) {
            return new ResponseEntity<WidgetClima>(widgetClima.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/buscarPorIdConBorrados/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<WidgetClima> findByIdWithDeleted(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<WidgetClima> widgetClima = service.findByIdWithDeleted(id);
        if (widgetClima.isPresent()) {
            return new ResponseEntity<WidgetClima>(widgetClima.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<WidgetClima> insert(@Valid @RequestBody WidgetClimaForm widgetClimaForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(widgetClimaForm.formWidgetClimaDTO(new WidgetClima())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<WidgetClima> update(@Valid @RequestBody WidgetClimaForm widgetClimaForm, @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<WidgetClima> widgetClima = service.findById(id);
        if (!widgetClima.isPresent()){
            System.out.println("No se encuentra el WidgetClima que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(widgetClimaForm.formWidgetClimaDTO(widgetClima.get())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<WidgetClima> widgetClima = service.findById(id);
        if (!widgetClima.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un WidgetClima con ese id");
        service.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El WidgetClima de id "+id+" ha sido eliminado correctamente");
    }

    @PostMapping(value = "/restaurar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> recycle(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<WidgetClima> widgetClima = service.findByIdWithDeleted(id);
        if (!widgetClima.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un WidgetClima con ese id");
        if (widgetClima.get().getBorrado()==null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El WidgetClima de id "+id+" no ha sido eliminado");
        service.recycle(widgetClima.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El WidgetClima de id "+id+" ha sido recuperado correctamente");
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> destroy(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<WidgetClima> widgetClima = service.findById(id);
        if (!widgetClima.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un WidgetClima con ese id");
        service.destroy(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El WidgetClima de id "+id+" ha sido destruido correctamente");
    }
}
