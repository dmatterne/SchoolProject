package be.david.school.repository_bean;

import be.david.school.dbmodel.Org_films;
import be.david.school.repository.Org_filmsRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class Org_filmsRepositoryBean implements Org_filmsRepository {

    private EntityManager em;

    public Org_filmsRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Org_films> findAllOrg_films() {
        return em.createQuery("select o from Org_films o", Org_films.class).getResultList();
    }
}
