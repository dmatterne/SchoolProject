package be.david.school;

import be.david.school.bean.CountryRepositoryBean;
import be.david.school.bean.RolesRepositoryBean;
import be.david.school.model.Country;
import be.david.school.model.Roles;
import be.david.school.repository.CountryRepository;
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
        assertEquals(5, rrs.size());
    }

}
