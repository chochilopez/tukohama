package com.gloit.tukohama.controller;

import com.gloit.tukohama.controller.form.PublicidadForm;
import com.gloit.tukohama.entity.Publicidad;
import com.gloit.tukohama.service.implementation.PublicidadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/publicidad")
public class PublicidadController {

    @Autowired
    PublicidadServiceImpl service;

    @PutMapping(value = "/guardarPublicidad",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Publicidad> guardarPublicidad(@RequestParam("file") MultipartFile multipartFile,
                                                        @RequestParam("firma") String firma,
                                                        @RequestParam("link") String link,
                                                        @RequestParam("importancia")Integer importancia,
                                                        @RequestParam("tipo")Integer tipo) {
        //FIXME SACAR OPTIONAL DEL CONTROLADOR Y DEL SERVICIO
        try{
            Publicidad archivo =service.guardarPublicidad(multipartFile, tipo, importancia, firma, link);
            if (archivo.getCreado()==null)
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            return new ResponseEntity<Publicidad>(archivo, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error al almacenar imagen "+e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/todas")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Publicidad>> getAll() {
        List<Publicidad> publicidads= service.findAll();
        if (publicidads.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Publicidad>>(publicidads, HttpStatus.OK);
    }

    @GetMapping(value = "/todasConBorradas")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Publicidad>> getAllWithDeleted() {
        List<Publicidad> publicidads= service.findAllWithDeleted();
        if (publicidads.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Publicidad>>(publicidads, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorId/{id}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<Publicidad> findById(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Publicidad> publicidad = service.findById(id);
        if (publicidad.isPresent()) {
            return new ResponseEntity<Publicidad>(publicidad.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/buscarPorIdConBorradas/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Publicidad> findByIdWithDeleted(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Publicidad> publicidad = service.findByIdWithDeleted(id);
        if (publicidad.isPresent()) {
            return new ResponseEntity<Publicidad>(publicidad.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Publicidad> insert(@Valid @RequestBody PublicidadForm publicidadForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(publicidadForm.formPublicidadDTO(new Publicidad())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Publicidad> update(@Valid @RequestBody PublicidadForm publicidadForm, @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Publicidad> publicidad = service.findById(id);
        if (!publicidad.isPresent()){
            System.out.println("No se encuentra el Publicidad que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(publicidadForm.formPublicidadDTO(publicidad.get())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Publicidad> publicidad = service.findById(id);
        if (!publicidad.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Publicidad con ese id");
        service.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Publicidad de id "+id+" ha sido eliminado correctamente");
    }

    @PostMapping(value = "/restaurar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> recycle(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Publicidad> publicidad = service.findByIdWithDeleted(id);
        if (!publicidad.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Publicidad con ese id");
        if (publicidad.get().getBorrado()==null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El Publicidad de id "+id+" no ha sido eliminado");
        service.recycle(publicidad.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Publicidad de id "+id+" ha sido recuperado correctamente");
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> destroy(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Publicidad> publicidad = service.findById(id);
        if (!publicidad.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Publicidad con ese id");
        service.destroy(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Publicidad de id "+id+" ha sido destruido correctamente");
    }
}
