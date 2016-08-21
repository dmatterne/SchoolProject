package be.david.school;

import be.david.school.repository_bean.Flm_crewsRepositoryBean;
import be.david.school.dbmodel.Flm_crews;
import be.david.school.repository.Flm_crewsRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class Flm_crewsPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllCountries() {
        Flm_crewsRepository fr = new Flm_crewsRepositoryBean(entityManager());
        List<Flm_crews> frs = fr.findAllFlm_crews();
        assertEquals(1, frs.size());
    }
}
