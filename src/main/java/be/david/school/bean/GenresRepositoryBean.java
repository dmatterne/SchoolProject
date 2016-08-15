package be.david.school.bean;

import be.david.school.model.Distributors;
import be.david.school.model.Genres;
import be.david.school.repository.DistributorsRepository;
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
