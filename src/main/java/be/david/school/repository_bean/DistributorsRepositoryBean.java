package be.david.school.repository_bean;

import be.david.school.dbmodel.Distributors;
import be.david.school.repository.DistributorsRepository;

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
