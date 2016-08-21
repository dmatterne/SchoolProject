package be.david.school.repository_bean;

import be.david.school.dbmodel.Tickets;
import be.david.school.repository.TicketsRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class TicketsRepositoryBean implements TicketsRepository {

    private EntityManager em;

    public TicketsRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Tickets> findAllTickets() {
        return em.createQuery("select t from Tickets t", Tickets.class).getResultList();
    }
}
