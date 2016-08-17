package be.david.school.bean;

import be.david.school.model.Customers;
import be.david.school.model.Logins;
import be.david.school.repository.CustomersRepository;
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
