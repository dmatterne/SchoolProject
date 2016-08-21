package be.david.school.repository_bean;

import be.david.school.dbmodel.Flm_scores;
import be.david.school.repository.Flm_scoresRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class Flm_scoresRepositoryBean implements Flm_scoresRepository {

    private EntityManager em;

    public Flm_scoresRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Flm_scores> findAllFlm_scores() {
        return em.createQuery("select f from Flm_scores f", Flm_scores.class).getResultList();
    }
}
