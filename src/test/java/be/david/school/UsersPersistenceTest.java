package be.david.school;

import be.david.school.repository_bean.UsersRepositoryBean;
import be.david.school.dbmodel.Users;
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
