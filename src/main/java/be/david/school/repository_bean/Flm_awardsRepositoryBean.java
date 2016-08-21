package be.david.school.repository_bean;

import be.david.school.dbmodel.Flm_awards;
import be.david.school.repository.Flm_awardsRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class Flm_awardsRepositoryBean implements Flm_awardsRepository {

    private EntityManager em;

    public Flm_awardsRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Flm_awards> findAllFlm_awards() {
        return em.createQuery("select f from Flm_awards f", Flm_awards.class).getResultList();
    }
}
