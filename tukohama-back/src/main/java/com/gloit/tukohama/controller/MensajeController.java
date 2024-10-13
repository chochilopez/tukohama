package com.gloit.tukohama.controller;

import com.gloit.tukohama.controller.form.MensajeForm;
import com.gloit.tukohama.entity.Mensaje;
import com.gloit.tukohama.service.implementation.MensajeServiceImpl;
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
@RequestMapping(value = "/api/mensaje")
public class MensajeController {

    @Autowired
    MensajeServiceImpl service;

    @GetMapping(value = "/todas")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Mensaje>> getAll() {
        List<Mensaje> mensaje = service.findAll();
        if (mensaje.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Mensaje>>(mensaje, HttpStatus.OK);
    }

    @GetMapping(value = "/todasConBorradas")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Mensaje>> getAllWithDeleted() {
        List<Mensaje> mensaje = service.findAllWithDeleted();
        if (mensaje.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Mensaje>>(mensaje, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorId/{id}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<Mensaje> findById(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Mensaje> mensaje = service.findById(id);
        if (mensaje.isPresent()) {
            return new ResponseEntity<Mensaje>(mensaje.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/buscarPorIdConBorradas/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Mensaje> findByIdWithDeleted(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Mensaje> mensaje = service.findByIdWithDeleted(id);
        if (mensaje.isPresent()) {
            return new ResponseEntity<Mensaje>(mensaje.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Mensaje> insert(@Valid @RequestBody MensajeForm mensajeForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(mensajeForm.formMensajeDTO(new Mensaje())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Mensaje> update(@Valid @RequestBody MensajeForm mensajeForm, @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Mensaje> mensaje = service.findById(id);
        if (!mensaje.isPresent()){
            System.out.println("No se encuentra el Mensaje que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(mensajeForm.formMensajeDTO(mensaje.get())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Mensaje> mensaje = service.findById(id);
        if (!mensaje.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Categoria con ese id");
        service.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Categoria de id "+id+" ha sido eliminado correctamente");
    }

    @PostMapping(value = "/restaurar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> recycle(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Mensaje> mensaje = service.findByIdWithDeleted(id);
        if (!mensaje.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Mensaje con ese id");
        if (mensaje.get().getBorrado()==null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El Mensaje de id "+id+" no ha sido eliminado");
        service.recycle(mensaje.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Mensaje de id "+id+" ha sido recuperado correctamente");
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> destroy(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Mensaje> mensaje = service.findById(id);
        if (!mensaje.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Categoria con ese id");
        service.destroy(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Categoria de id "+id+" ha sido destruido correctamente");
    }
}
