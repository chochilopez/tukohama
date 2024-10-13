package com.gloit.tukohama.controller;

import com.gloit.tukohama.controller.form.NoticiaForm;
import com.gloit.tukohama.entity.*;
import com.gloit.tukohama.service.implementation.AutorServiceImpl;
import com.gloit.tukohama.service.implementation.CategoriaServiceImpl;
import com.gloit.tukohama.service.implementation.MultimediaServiceImpl;
import com.gloit.tukohama.service.implementation.NoticiaServiceImpl;
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
@RequestMapping(value = "/api/noticia")
public class NoticiaController {

    @Autowired
    NoticiaServiceImpl service;

    @Autowired
    AutorServiceImpl autorService;

    @Autowired
    CategoriaServiceImpl categoriaService;

    @Autowired
    MultimediaServiceImpl multimediaService;

    @GetMapping(value = "/buscarNoticiasMasVistas/{cantidad}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Noticia>> buscarNoticiasMasVistas(@PathVariable(name = "cantidad") @javax.validation.constraints.Size(min = 1, max = 2) Integer cantidad) {
        List<Noticia> noticias = service.buscarNoticiasMasVistas(cantidad);
        if (noticias.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorCategoriaPublicadasLimitadas/{categoria}/{cantidad}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Noticia>> buscarPorCategoriaPublicadasLimitadas(
      @PathVariable(name = "categoria") @javax.validation.constraints.Size(min = 1, max = 50) String categoria,
      @PathVariable(name = "cantidad") @javax.validation.constraints.Size(min = 1, max = 5) Integer cantidad) {
        List<Noticia> noticias = service.buscarPorCategoriaPublicadasPortada(categoria, cantidad);
        if (noticias.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPublicadaPorTiempo/{inicio}/{fin}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Noticia>> buscarPublicadaPorTiempo
      (@PathVariable(name = "inicio") @javax.validation.constraints.Size(min = 3, max = 30) String inicio ,
       @PathVariable(name = "fin") @javax.validation.constraints.Size(min = 3, max = 30) String fin) throws Exception{
        List<Noticia> noticias = service.buscarPublicadaPorTiempo(inicio, fin);
        if (noticias.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorTituloPublicada/{titulo}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Noticia>> buscarPorTituloPublicada
    (@PathVariable(name = "titulo") @javax.validation.constraints.Size(min = 3, max = 30) String titulo) {
        List<Noticia> noticias = service.buscarPorTituloPublicada(titulo);
        if (noticias.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorIdPublicada/{id}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<Noticia> buscarPorIdPublicada(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Noticia> noticia = service.buscarPorIdPublicada(id);
        if (noticia.isPresent()) {
            return new ResponseEntity<Noticia>(noticia.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/publicar/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Noticia> publicar(@Valid @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Noticia noticia = service.publicar(id);
        if (noticia==null){
            System.out.println("No se encuentra la noticia que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(noticia);
    }

    @GetMapping("/despublicar/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Noticia> despublicar(@Valid @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Noticia noticia = service.despublicar(id);
        if (noticia==null){
            System.out.println("No se encuentra la noticia que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(noticia);
    }

    //TODO Esto se podria hacer en vue
    @GetMapping(value = "/cantidadPublicadas")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Long> cantidadPublicadas() {
        Long cantidad= service.cantidadPublicadas();
        if (cantidad == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<Long>(cantidad, HttpStatus.OK);
    }

    @GetMapping(value = "/cantidadBorradores")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Long> cantidadBorradores() {
        Long cantidad= service.cantidadBorradores();
        if (cantidad == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<Long>(cantidad, HttpStatus.OK);
    }


    @GetMapping(value = "/buscarPublicadasPortada")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Noticia>> buscarPublicadasPortada() {
        List<Noticia> noticias = service.buscarPublicadasPortada();
        if (noticias.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPublicadas")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Noticia>> buscarPublicadas() {
        List<Noticia> noticias = service.buscarPublicadas();
        if (noticias.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarBorradas")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Noticia>> buscarBorradas() {
        List<Noticia> noticias = service.buscarBorradas();
        if (noticias.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarBorradores")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<List<Noticia>> buscarBorradores() {
        List<Noticia> noticias = service.buscarBorradores();
        if (noticias.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorImportanciaPublicadasPortada/{importancia}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Noticia>> buscarPorImportanciaPublicadasPortada
      (@PathVariable(name = "importancia") @javax.validation.constraints.Size(min = 1, max = 50) Integer importancia) {
        List<Noticia> noticias = service.buscarPorImportanciaPublicadasPortada(importancia);
        if (noticias.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorCategoriaPublicadas/{categoria}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Noticia>> buscarPorCategoriaPublicadas(@PathVariable(name = "categoria") @javax.validation.constraints.Size(min = 1, max = 50) String categoria) {
        List<Noticia> noticias = service.buscarPorCategoriaPublicadas(categoria);
        if (noticias.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);
    }

    @GetMapping(value = "/todas")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<List<Noticia>> getAll() {
        List<Noticia> noticias= service.findAll();
        if (noticias.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);
    }

    @GetMapping(value = "/todasConBorradas")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Noticia>> getAllWithDeleted() {
        List<Noticia> noticias= service.findAllWithDeleted();
        if (noticias.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorId/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Noticia> findById(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Noticia> noticia = service.findById(id);
        if (noticia.isPresent()) {
            return new ResponseEntity<Noticia>(noticia.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/buscarPorIdConBorradas/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Noticia> findByIdWithDeleted(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Noticia> noticia = service.findByIdWithDeleted(id);
        if (noticia.isPresent()) {
            return new ResponseEntity<Noticia>(noticia.get(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Noticia> insert(@Valid @RequestBody NoticiaForm noticiaForm, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        Noticia noticia=new Noticia();
        if (noticiaForm.getIdAutor()!=null){
            Optional<Autor> autor=autorService.findById(noticiaForm.getIdAutor());
            if (autor.isPresent())
                noticia.setAutor(autor.get());
        }
        if (noticiaForm.getIdCategoria()!=null){
            Optional<Categoria> categoria=categoriaService.findById(noticiaForm.getIdCategoria());
            if (categoria.isPresent())
                noticia.setCategoria(categoria.get());
        }
        if (noticiaForm.getIdImagenPortada()!=null){
            Optional<Multimedia> multimedia=multimediaService.findById(noticiaForm.getIdImagenPortada());
            if (multimedia.isPresent()){
                noticia.setImagenPortada(multimedia.get());
            }
        }
        Set<Multimedia> audios=new HashSet<Multimedia>();
        if (noticiaForm.getIdsAudios()!=null){
            for (Long ids:noticiaForm.getIdsAudios()) {
                Optional<Multimedia> audio = multimediaService.findById(ids);
                if (audio.isPresent())
                    audios.add(audio.get());
            }
        }
        if (!audios.isEmpty())
            noticia.setAudios(audios);

        Set<Multimedia> imagenes=new HashSet<Multimedia>();
        if (noticiaForm.getIdsImagenes()!=null){
            for (Long ids:noticiaForm.getIdsImagenes()) {
                Optional<Multimedia> imagen = multimediaService.findById(ids);
                if (imagen.isPresent())
                    imagenes.add(imagen.get());
            }
        }
        if (!imagenes.isEmpty())
            noticia.setImagenes(imagenes);

        Set<Multimedia> videos=new HashSet<Multimedia>();
        if (noticiaForm.getIdsVideos()!=null){
            for (Long ids:noticiaForm.getIdsVideos()) {
                Optional<Multimedia> video = multimediaService.findById(ids);
                if (video.isPresent())
                    videos.add(video.get());
            }
        }
        if (!videos.isEmpty())
            noticia.setVideos(videos);

        Noticia respuesta=service.insert(noticiaForm.formNoticiaDTO(noticia));


        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<Noticia> update(@Valid @RequestBody NoticiaForm noticiaForm, @PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Noticia> noticia = service.findById(id);
        if (!noticia.isPresent()){
            System.out.println("No se encuentra la noticia que desea modificar.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        if (noticiaForm.getIdAutor()!=null){
            Optional<Autor> autor=autorService.findById(noticiaForm.getIdAutor());
            if (autor.isPresent())
                noticia.get().setAutor(autor.get());
        }
        if (noticiaForm.getIdCategoria()!=null){
            Optional<Categoria> categoria=categoriaService.findById(noticiaForm.getIdCategoria());
            if (categoria.isPresent())
                noticia.get().setCategoria(categoria.get());
        }
        if (noticiaForm.getIdImagenPortada()!=null){
            Optional<Multimedia> multimedia=multimediaService.findById(noticiaForm.getIdImagenPortada());
            if (multimedia.isPresent())
                noticia.get().setImagenPortada(multimedia.get());
        }
        Set<Multimedia> audios=new HashSet<Multimedia>();
        if (noticiaForm.getIdsAudios()!=null){
            for (Long ids:noticiaForm.getIdsAudios()) {
                Optional<Multimedia> audio = multimediaService.findById(ids);
                if (audio.isPresent())
                    audios.add(audio.get());
            }
        }
        if (!audios.isEmpty())
            noticia.get().setAudios(audios);

        Set<Multimedia> imagenes=new HashSet<Multimedia>();
        if (noticiaForm.getIdsImagenes()!=null){
            for (Long ids:noticiaForm.getIdsImagenes()) {
                Optional<Multimedia> imagen = multimediaService.findById(ids);
                if (imagen.isPresent())
                    imagenes.add(imagen.get());
            }
        }
        if (!imagenes.isEmpty())
            noticia.get().setImagenes(imagenes);

        Set<Multimedia> videos=new HashSet<Multimedia>();
        if (noticiaForm.getIdsVideos()!=null){
            for (Long ids:noticiaForm.getIdsVideos()) {
                Optional<Multimedia> video = multimediaService.findById(ids);
                if (video.isPresent())
                    videos.add(video.get());
            }
        }
        if (!videos.isEmpty())
            noticia.get().setVideos(videos);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(noticiaForm.formNoticiaDTO(noticia.get())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Noticia> noticia = service.findById(id);
        if (!noticia.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Noticia con ese id");
        service.delete(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Noticia de id "+id+" ha sido eliminado correctamente");
    }

    @DeleteMapping(value = "/restaurar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> recycle(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Noticia> noticia = service.findByIdWithDeleted(id);
        if (!noticia.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Noticia con ese id");
        if (noticia.get().getBorrado()==null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El Noticia de id "+id+" no ha sido eliminado");
        service.recycle(noticia.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Noticia de id "+id+" ha sido recuperado correctamente");
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> destroy(@PathVariable(name = "id") @javax.validation.constraints.Size(min = 1, max = 5) Long id) {
        Optional<Noticia> noticia = service.findById(id);
        if (!noticia.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe un Noticia con ese id");
        service.destroy(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Noticia de id "+id+" ha sido destruido correctamente");
    }
}
