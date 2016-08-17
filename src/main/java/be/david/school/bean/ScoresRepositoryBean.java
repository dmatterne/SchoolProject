package be.david.school.bean;

import be.david.school.model.Customers;
import be.david.school.model.Scores;
import be.david.school.repository.CustomersRepository;
import be.david.school.repository.ScoresRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class ScoresRepositoryBean implements ScoresRepository {

    private EntityManager em;

    public ScoresRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Scores> findAllScores() {
        return em.createQuery("select s from Scores s", Scores.class).getResultList();
    }
}
