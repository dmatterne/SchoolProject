package be.david.school;

import be.david.school.bean.CountryRepositoryBean;
import be.david.school.bean.UsersRepositoryBean;
import be.david.school.model.Country;
import be.david.school.model.Users;
import be.david.school.repository.CountryRepository;
import be.david.school.repository.UsersRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class UsersPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllUsers() {
        UsersRepository ur = new UsersRepositoryBean(entityManager());
        List<Users> urs = ur.findAllUsers();
        assertEquals(1, urs.size());
    }

}
