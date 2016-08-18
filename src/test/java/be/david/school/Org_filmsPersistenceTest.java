package be.david.school;

import be.david.school.repository_bean.Org_filmsRepositoryBean;
import be.david.school.model.Org_films;
import be.david.school.repository.Org_filmsRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class Org_filmsPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllOrg_films() {
        Org_filmsRepository org = new Org_filmsRepositoryBean(entityManager());
        List<Org_films> orgs = org.findAllOrg_films();
        assertEquals(2, orgs.size());
    }
}
