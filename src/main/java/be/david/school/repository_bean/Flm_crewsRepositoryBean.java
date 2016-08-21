package be.david.school.repository_bean;

import be.david.school.dbmodel.Flm_crews;
import be.david.school.repository.Flm_crewsRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class Flm_crewsRepositoryBean implements Flm_crewsRepository {

    private EntityManager em;

    public Flm_crewsRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Flm_crews> findAllFlm_crews() {
        return em.createQuery("select f from Flm_crews f", Flm_crews.class).getResultList();
    }
}
