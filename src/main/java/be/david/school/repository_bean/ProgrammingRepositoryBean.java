package be.david.school.repository_bean;

import be.david.school.dbmodel.Programming;
import be.david.school.repository.ProgrammingRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class ProgrammingRepositoryBean implements ProgrammingRepository {

    private EntityManager em;

    public ProgrammingRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Programming> findAllProgramming() {
        return em.createQuery("select p from Programming p", Programming.class).getResultList();
    }
}
