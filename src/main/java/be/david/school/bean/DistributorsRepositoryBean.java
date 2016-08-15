package be.david.school.bean;

import be.david.school.model.Distributors;
import be.david.school.model.Formats;
import be.david.school.repository.DistributorsRepository;
import be.david.school.repository.FormatsRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class DistributorsRepositoryBean implements DistributorsRepository {

    private EntityManager em;

    public DistributorsRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Distributors> findAllDistributors() {
        return em.createQuery("select d from Distributors d", Distributors.class).getResultList();
    }
}
