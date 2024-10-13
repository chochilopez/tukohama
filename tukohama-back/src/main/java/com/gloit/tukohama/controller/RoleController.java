package com.gloit.tukohama.controller;

import com.gloit.tukohama.controller.form.RoleForm;
import com.gloit.tukohama.entity.Role;
import com.gloit.tukohama.service.implementation.RoleServiceImpl;
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
@RequestMapping(value = "/api/role")
public class RoleController {

    @Autowired
    RoleServiceImpl service;

    @GetMapping(value = "/todos")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<List<Role>> getAll() {
        List<Role> role= service.findAll();
        if (role.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Role>>(role, HttpStatus.OK);
    }

    @GetMapping(value = "/todosConBorrados")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Role>> getAllWithDeleted() {
        List<Role> role= service.findAllWithDeleted();
        if (role.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Role>>(role, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorId/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Role> findById(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Role> role = service.findById(id);
        if (role.isPresent()) {
            return new ResponseEntity<Role>(role.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/buscarPorIdConBorrados/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Role> findByIdWithDeleted(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Role> role = service.findByIdWithDeleted(id);
        if (role.isPresent()) {
            return new ResponseEntity<Role>(role.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Role> insert(@Valid @RequestBody RoleForm roleForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(roleForm.formRoleDTO(new Role())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Role> update(@Valid @RequestBody RoleForm roleForm, @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Role> role = service.findById(id);
        if (!role.isPresent()){
            System.out.println("No se encuentra el Role que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(roleForm.formRoleDTO(role.get())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Role> role = service.findById(id);
        if (!role.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Role con ese id");
        service.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Role de id "+id+" ha sido eliminado correctamente");
    }

    @PostMapping(value = "/restaurar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> recycle(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Role> role = service.findByIdWithDeleted(id);
        if (!role.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Role con ese id");
        if (role.get().getBorrado()==null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El Role de id "+id+" no ha sido eliminado");
        service.recycle(role.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Role de id "+id+" ha sido recuperado correctamente");
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> destroy(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Role> role = service.findById(id);
        if (!role.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Role con ese id");
        service.destroy(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Role de id "+id+" ha sido destruido correctamente");
    }
}
