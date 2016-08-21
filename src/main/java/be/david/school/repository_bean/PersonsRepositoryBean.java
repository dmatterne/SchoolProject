package be.david.school.repository_bean;

import be.david.school.dbmodel.Persons;
import be.david.school.repository.PersonsRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class PersonsRepositoryBean implements PersonsRepository {

    private EntityManager em;

    public PersonsRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Persons> findAllPersons() {
        return em.createQuery("select p from Persons p", Persons.class).getResultList();
    }
}
