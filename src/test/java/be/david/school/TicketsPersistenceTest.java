package be.david.school;

import be.david.school.bean.CountryRepositoryBean;
import be.david.school.bean.TicketsRepositoryBean;
import be.david.school.model.Country;
import be.david.school.model.Tickets;
import be.david.school.repository.CountryRepository;
import be.david.school.repository.TicketsRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class TicketsPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllTickets() {
        TicketsRepository tr = new TicketsRepositoryBean(entityManager());
        List<Tickets> trs = tr.findAllTickets();
        assertEquals(2, trs.size());
    }

}
