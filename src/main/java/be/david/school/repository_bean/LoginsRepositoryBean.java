package be.david.school.repository_bean;

import be.david.school.dbmodel.Logins;
import be.david.school.repository.LoginsRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class LoginsRepositoryBean implements LoginsRepository {

    private EntityManager em;

    public LoginsRepositoryBean(EntityManager em) {
        this.em = em;
    }

    public List<Logins> findAllLogins() {
        return em.createQuery("select l from Logins l", Logins.class).getResultList();
    }
}
