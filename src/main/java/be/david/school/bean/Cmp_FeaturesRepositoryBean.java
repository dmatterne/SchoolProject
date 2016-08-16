package be.david.school.bean;

import be.david.school.model.Cmp_features;
import be.david.school.model.Country;
import be.david.school.repository.Cmp_FeaturesRepository;
import be.david.school.repository.CountryRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class Cmp_FeaturesRepositoryBean implements Cmp_FeaturesRepository {

    private EntityManager em;

    public Cmp_FeaturesRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Cmp_features> findAllCmpFeatures() {
        return em.createQuery("select c from Cmp_features c", Cmp_features.class).getResultList();
    }
}
