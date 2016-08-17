package be.david.school.bean;

import be.david.school.model.Country;
import be.david.school.model.Customers;
import be.david.school.repository.CountryRepository;
import be.david.school.repository.CustomersRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class CustomersRepositoryBean implements CustomersRepository {

    private EntityManager em;

    public CustomersRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Customers> findAllCustomers() {
        return em.createQuery("select c from Customers c", Customers.class).getResultList();
    }
}