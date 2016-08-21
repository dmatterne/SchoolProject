package be.david.school.repository_bean;

import be.david.school.dbmodel.Flm_reviews;
import be.david.school.repository.Flm_reviewsRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class Flm_reviewsRepositoryBean implements Flm_reviewsRepository {

    private EntityManager em;

    public Flm_reviewsRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Flm_reviews> findAllFlm_reviews() {
        return em.createQuery("select f from Flm_reviews f", Flm_reviews.class).getResultList();
    }
}
