package be.david.school.repository_bean;

import be.david.school.dbmodel.Ntn_films;
import be.david.school.repository.Ntn_filmsRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class Ntn_filmsRepositoryBean implements Ntn_filmsRepository {

    private EntityManager em;

    public Ntn_filmsRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Ntn_films> findAllNtn_films() {
        return em.createQuery("select n from Ntn_films n", Ntn_films.class).getResultList();
    }
}
