package com.gloit.tukohama.repository;

import com.gloit.tukohama.entity.Noticia;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class NoticiaRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Noticia> buscarPorNoticiasPorCategoriaLimitadas(String categoria, Integer limit) {
        String consulta = "select obj from Noticia obj where obj.borrado is null and obj.publicado is not null and obj.categoria.nombre='"+categoria+"' order by obj.publicado DESC";
        return entityManager.createQuery(consulta, Noticia.class).setMaxResults(limit).getResultList();
    }

    public List<Noticia> buscarNoticiasMasVistas(Integer limit) {
        String consulta = "select obj from Noticia obj where obj.borrado is null and obj.publicado is not null order by obj.visitas DESC";
        return entityManager.createQuery(consulta, Noticia.class).setMaxResults(limit).getResultList();
    }
}
