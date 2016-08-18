package be.david.school;

import be.david.school.bean.CountryRepositoryBean;
import be.david.school.bean.LoginsRepositoryBean;
import be.david.school.model.Country;
import be.david.school.model.Logins;
import be.david.school.repository.CountryRepository;
import be.david.school.repository.LoginsRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class LoginsPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllLogins() {
        LoginsRepository lr = new LoginsRepositoryBean(entityManager());
        List<Logins> lrs = lr.findAllLogins();
        assertEquals(1, lrs.size());
    }
}
