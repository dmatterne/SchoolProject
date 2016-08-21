package be.david.school.repository_bean;

import be.david.school.dbmodel.Flm_info;
import be.david.school.repository.Flm_infoRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class Flm_infoRepositoryBean implements Flm_infoRepository {

    private EntityManager em;

    public Flm_infoRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Flm_info> findAllFlm_info() {
        return em.createQuery("select f from Flm_info f", Flm_info.class).getResultList();
    }
}
