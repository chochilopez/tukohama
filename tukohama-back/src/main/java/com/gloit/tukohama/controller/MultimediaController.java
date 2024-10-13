package com.gloit.tukohama.controller;

import com.gloit.tukohama.controller.form.MultimediaForm;
import com.gloit.tukohama.entity.Multimedia;
import com.gloit.tukohama.service.implementation.MultimediaServiceImpl;
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
@RequestMapping(value = "/api/multimedia")
public class MultimediaController {

    @Autowired
    MultimediaServiceImpl service;

    @GetMapping(value = "/buscarAudios")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<List<Multimedia>> buscarAudios() {
        List<Multimedia> multimedias = service.buscarMultimedia(0);
        if (multimedias.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return new ResponseEntity<List<Multimedia>>(multimedias, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarImagenes")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<List<Multimedia>> buscarImagenes() {
        List<Multimedia> multimedias = service.buscarMultimedia(1);
        if (multimedias.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return new ResponseEntity<List<Multimedia>>(multimedias, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarVideos")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<List<Multimedia>> buscaVideos() {
        List<Multimedia> buscarAudios = service.buscarMultimedia(2);
        if (buscarAudios.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return new ResponseEntity<List<Multimedia>>(buscarAudios, HttpStatus.OK);
    }

    @PutMapping(value = "/guardarAudio",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Multimedia> guardaAudio(
      @RequestParam("file") MultipartFile multipartFile,
      @RequestParam("nombre") String nombre,
      @RequestParam("tamanio") String tamanio,
      @RequestParam("descripcion") String descripcion) {
        try{
            Multimedia archivo =service.guardarMultimedia(multipartFile, 0, nombre, descripcion, tamanio);
            if (archivo.getCreado()==null)
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            return new ResponseEntity<Multimedia>(archivo, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error al almacenar audio "+e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value = "/guardarImagen",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Multimedia> guardarImagen(
      @RequestParam("file") MultipartFile multipartFile,
      @RequestParam("nombre") String nombre,
      @RequestParam("tamanio") String tamanio,
      @RequestParam("descripcion") String descripcion) {
        try{
            Multimedia archivo =service.guardarMultimedia(multipartFile, 1, nombre, descripcion, tamanio);
            if (archivo.getCreado()==null)
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            return new ResponseEntity<Multimedia>(archivo, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error al almacenar imagen "+e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value = "/guardarVideo",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Multimedia> guardarVideo(
      @RequestParam("file") MultipartFile multipartFile,
      @RequestParam("nombre") String nombre,
      @RequestParam("tamanio") String tamanio,
      @RequestParam("descripcion") String descripcion) {
        try{
            Multimedia archivo =service.guardarMultimedia(multipartFile, 2, nombre, descripcion, tamanio);
            if (archivo.getCreado()==null)
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            return new ResponseEntity<Multimedia>(archivo, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error al almacenar video "+e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/todas")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Multimedia>> getAll() {
        List<Multimedia> multimedia= service.findAll();
        if (multimedia.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Multimedia>>(multimedia, HttpStatus.OK);
    }

    @GetMapping(value = "/todasConBorradas")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Multimedia>> getAllWithDeleted() {
        List<Multimedia> multimedia= service.findAllWithDeleted();
        if (multimedia.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Multimedia>>(multimedia, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorId/{id}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<Multimedia> findById(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Multimedia> imagen = service.findById(id);
        if (imagen.isPresent()) {
            return new ResponseEntity<Multimedia>(imagen.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/buscarPorIdConBorradas/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Multimedia> findByIdWithDeleted(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Multimedia> imagen = service.findByIdWithDeleted(id);
        if (imagen.isPresent()) {
            return new ResponseEntity<Multimedia>(imagen.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Multimedia> insert(@Valid @RequestBody MultimediaForm multimediaForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(multimediaForm.formImagenDTO(new Multimedia())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Multimedia> update(@Valid @RequestBody MultimediaForm multimediaForm, @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Multimedia> imagen = service.findById(id);
        if (!imagen.isPresent()){
            System.out.println("No se encuentra el Multimedia que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(multimediaForm.formImagenDTO(imagen.get())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Multimedia> imagen = service.findById(id);
        if (!imagen.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Multimedia con ese id");
        service.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Multimedia de id "+id+" ha sido eliminado correctamente");
    }

    @PostMapping(value = "/restaurar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> recycle(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Multimedia> imagen = service.findByIdWithDeleted(id);
        if (!imagen.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Multimedia con ese id");
        if (imagen.get().getBorrado()==null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El Multimedia de id "+id+" no ha sido eliminado");
        service.recycle(imagen.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Multimedia de id "+id+" ha sido recuperado correctamente");
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> destroy(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Multimedia> imagen = service.findById(id);
        if (!imagen.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Multimedia con ese id");
        service.destroy(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Multimedia de id "+id+" ha sido destruido correctamente");
    }
}
