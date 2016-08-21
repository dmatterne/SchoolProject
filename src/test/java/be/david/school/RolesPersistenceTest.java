package be.david.school;

import be.david.school.repository_bean.RolesRepositoryBean;
import be.david.school.dbmodel.Roles;
import be.david.school.repository.RolesRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class RolesPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllRoles() {
        RolesRepository rr = new RolesRepositoryBean(entityManager());
        List<Roles> rrs = rr.findAllRoles();
        assertEquals(3, rrs.size());
    }

}
