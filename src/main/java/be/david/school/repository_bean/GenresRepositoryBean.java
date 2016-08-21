package be.david.school.repository_bean;

import be.david.school.dbmodel.Genres;
import be.david.school.repository.GenresRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class GenresRepositoryBean implements GenresRepository {

    private EntityManager em;

    public GenresRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Genres> findAllGenres() {
        return em.createQuery("select g from Genres g", Genres.class).getResultList();
    }
}
