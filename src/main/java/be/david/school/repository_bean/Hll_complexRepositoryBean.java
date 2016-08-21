package be.david.school.repository_bean;

import be.david.school.dbmodel.Hll_complex;
import be.david.school.repository.Hll_complexRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class Hll_complexRepositoryBean implements Hll_complexRepository {

    private EntityManager em;

    public Hll_complexRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Hll_complex> findAllHll_complex() {
        return em.createQuery("select h from Hll_complex h", Hll_complex.class).getResultList();
    }
}
