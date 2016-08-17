package be.david.school;

import be.david.school.bean.CountryRepositoryBean;
import be.david.school.bean.PersonsRepositoryBean;
import be.david.school.model.Country;
import be.david.school.model.Persons;
import be.david.school.repository.CountryRepository;
import be.david.school.repository.PersonsRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class PersonsPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllPersons() {
        PersonsRepository pr = new PersonsRepositoryBean(entityManager());
        List<Persons> prs = pr.findAllPersons();
        assertEquals(5, prs.size());
    }
}
