package be.david.school.repository_bean;

import be.david.school.dbmodel.Lng_versions;
import be.david.school.repository.Lng_versionsRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class Lng_versionsRepositoryBean implements Lng_versionsRepository {

    private EntityManager em;

    public Lng_versionsRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Lng_versions> findAllLng_versions() {
        return em.createQuery("select l from Lng_versions l", Lng_versions.class).getResultList();
    }
}
