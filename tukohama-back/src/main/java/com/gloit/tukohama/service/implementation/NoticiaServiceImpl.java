package com.gloit.tukohama.service.implementation;

import com.gloit.tukohama.entity.Noticia;
import com.gloit.tukohama.helper.Helper;
import com.gloit.tukohama.repository.NoticiaRepository;
import com.gloit.tukohama.repository.NoticiaRepositoryImpl;
import com.gloit.tukohama.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NoticiaServiceImpl implements NoticiaService {

    @Autowired
    private NoticiaRepository repositorio;

    @Autowired
    private NoticiaRepositoryImpl noticiaRepository;

    private Authentication authentication;

    @Override
    public Noticia addVisita(Long id){
        Optional<Noticia> noticia=repositorio.findById(id);
        if (noticia.isEmpty())
            return null;
        noticia.get().setVistas(noticia.get().getVistas()+1);
        Noticia respuesta=repositorio.save(noticia.get());

        return respuesta;
    };

    @Override
    public List<Noticia> buscarNoticiasMasVistas(Integer cantidad){
        List<Noticia> result = noticiaRepository.buscarNoticiasMasVistas(cantidad);
        if (result.isEmpty())
            System.out.println("Resultado vacio para la consulta noticias mas vistas en la tabla Noticia.");
        return result;
    };

    @Override
    public List<Noticia> buscarPorCategoriaPublicadasPortada(String categoria, Integer cantidad){
        List<Noticia> result = noticiaRepository.buscarPorNoticiasPorCategoriaLimitadas(categoria, cantidad);
        if (result.isEmpty())
            System.out.println("Resultado vacio para la consulta categoria ->"+categoria+"<- en la tabla Noticia.");
        return result;
    };

    @Override
    public List<Noticia> buscarPublicadaPorTiempo(String inicio, String fin) throws Exception{
        // yyyy-mm-dd HH:mm:ss
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        Date fechaInicio = format.parse(inicio);
        Date fechaFin = format.parse(fin);
        List<Noticia> result = repositorio.buscarPublicadaPorTiempo(fechaInicio, fechaFin);
        if (result.isEmpty())
            System.out.println("Resultado vacio para la consulta tiempo en la tabla Noticia.");
        return result;
    };

    @Override
    public List<Noticia> buscarPorTituloPublicada(String titulo){
        List<Noticia> result = repositorio.buscarPorTituloPublicada(titulo);
        if (result.isEmpty())
            System.out.println("Resultado vacio para la consulta titulo en la tabla Noticia.");
        return result;
    };

    @Override
    public Optional<Noticia> buscarPorIdPublicada(Long id){
        Optional<Noticia> result=repositorio.buscarPorIdPublicada(id);
        return result;
    };

    @Override
    public Noticia despublicar(Long id){
        Optional<Noticia> noticia=repositorio.findById(id);
        if (noticia.isEmpty())
            return null;
        authentication = SecurityContextHolder.getContext().getAuthentication();
        noticia.get().setPublicado(null);
        noticia.get().setPublicador(null);
        Noticia respuesta=repositorio.save(noticia.get());

        return respuesta;
    };

    @Override
    public Noticia publicar(Long id){
        Optional<Noticia> noticia=repositorio.findById(id);
        if (noticia.isEmpty())
            return null;
        authentication = SecurityContextHolder.getContext().getAuthentication();
        noticia.get().setPublicado(Helper.getToday());
        noticia.get().setPublicador(authentication.getName());
        Noticia respuesta=repositorio.save(noticia.get());

        return respuesta;
    };

    @Override
    public Long cantidadPublicadas(){
        return repositorio.cantidadPublicadas();
    }

    @Override
    public Long cantidadBorradores(){
        return repositorio.cantidadBorradores();
    }

    @Override
    public List<Noticia> buscarPublicadas(){
        List<Noticia> result = repositorio.buscarPublicadas();
        if (result.isEmpty())
            System.out.println("Resultado vacio para la consulta publicada en la tabla Noticia.");
        return result;
    };

    @Override
    public List<Noticia> buscarBorradas(){
        List<Noticia> result = repositorio.buscarBorradas();
        if (result.isEmpty())
            System.out.println("Resultado vacio para la consulta borradas en la tabla Noticia.");
        return result;
    };

    @Override
    public List<Noticia> buscarBorradores(){
        List<Noticia> result = repositorio.buscarBorradores();
        if (result.isEmpty())
            System.out.println("Resultado vacio para la consulta borradores en la tabla Noticia.");
        return result;
    };

    @Override
    public List<Noticia> buscarPublicadasPortada(){
        List<Noticia> result = repositorio.buscarPublicadasPortada();
        if (result.isEmpty())
            System.out.println("Resultado vacio para la consulta publicada en la tabla Noticia.");
        return result;
    };

    @Override
    public List<Noticia> buscarPorImportanciaPublicadasPortada(Integer importancia){
        List<Noticia> result = repositorio.buscarPorImportanciaPublicadasPortada(importancia);
        if (result.isEmpty())
            System.out.println("Resultado vacio para la consulta importancia ->"+importancia+"<- en la tabla Noticia.");
        return result;
    };

    @Override
    public List<Noticia> buscarPorCategoriaPublicadas(String categoria){
        List<Noticia> result = repositorio.buscarPorCategoriaPublicadas(categoria);
        if (result.isEmpty())
            System.out.println("Resultado vacio para la consulta categoria ->"+categoria+"<- en la tabla Noticia.");
        return result;
    };

    @Override
    public Optional<Noticia> findById(Long id){
        Optional<Noticia> result=repositorio.findById(id);
        return result;
    };

    @Override
    public List<Noticia> findAll(){
        List<Noticia> result = repositorio.findAll();
        if (result.isEmpty())
            System.out.println("La tabla Noticia vacia, pero puede tener elementos reciclables.  ");
        return result;
    };

    @Override
    public Optional<Noticia> findByIdWithDeleted(Long id){
        Optional<Noticia> result=repositorio.findByIdWithDeleted(id);
        return result;
    };

    @Override
    public List<Noticia> findAllWithDeleted(){
        List<Noticia> result = repositorio.findAllWithDeleted();
        if (result.isEmpty())
            System.out.println("La tabla Noticia esta vacia.");
        return result;
    };

    @Override
    public Noticia insert(Noticia obj){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setCreado(Helper.getToday());
        obj.setCreador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public Noticia update(Noticia obj) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        obj.setModificado(Helper.getToday());
        obj.setModificador(authentication.getName());
        obj=repositorio.save(obj);
        return obj;
    }

    @Override
    public void delete(Long id){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Noticia> obj=repositorio.findById(id);
        obj.get().setBorrado(Helper.getToday());
        obj.get().setBorrador(authentication.getName());
        repositorio.save(obj.get());
    };

    @Override
    public Noticia recycle(Noticia obj){
        obj.setBorrado(null);
        obj.setBorrador(null);
        obj.setPublicado(null);
        obj=repositorio.save(obj);
        return obj;
    };

    @Override
    public void destroy(Long id){
        repositorio.deleteById(id);
    };
}

