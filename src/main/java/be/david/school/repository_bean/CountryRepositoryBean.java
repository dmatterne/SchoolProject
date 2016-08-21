package be.david.school.repository_bean;

import be.david.school.dbmodel.Country;
import be.david.school.repository.CountryRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class CountryRepositoryBean implements CountryRepository {

    private EntityManager em;

    public CountryRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Country> findAllCountries() {
        return em.createQuery("select c from Country c", Country.class).getResultList();
    }
}
