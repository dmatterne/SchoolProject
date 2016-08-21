package be.david.school.repository_bean;

import be.david.school.dbmodel.Discounts;
import be.david.school.repository.DiscountsRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class DiscountsRepositoryBean implements DiscountsRepository {

    private EntityManager em;

    public DiscountsRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Discounts> findAllDiscounts() {
        return em.createQuery("select d from Discounts d", Discounts.class).getResultList();
    }
}
