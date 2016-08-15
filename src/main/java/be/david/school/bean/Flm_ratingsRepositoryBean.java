package be.david.school.bean;

import be.david.school.model.Country;
import be.david.school.model.Flm_ratings;
import be.david.school.repository.CountryRepository;
import be.david.school.repository.Flm_ratingsRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class Flm_ratingsRepositoryBean implements Flm_ratingsRepository {

    private EntityManager em;

    public Flm_ratingsRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Flm_ratings> findAllFLMRatings() {
        return em.createQuery("select f from Flm_ratings f", Flm_ratings.class).getResultList();
    }
}
