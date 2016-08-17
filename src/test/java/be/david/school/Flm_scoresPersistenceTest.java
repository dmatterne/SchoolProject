package be.david.school;

import be.david.school.bean.CountryRepositoryBean;
import be.david.school.bean.Flm_scoresRepositoryBean;
import be.david.school.model.Country;
import be.david.school.model.Flm_scores;
import be.david.school.repository.CountryRepository;
import be.david.school.repository.Flm_scoresRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class Flm_scoresPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllFlm_scores() {
        Flm_scoresRepository fr = new Flm_scoresRepositoryBean(entityManager());
        List<Flm_scores> frs = fr.findAllFlm_scores();
        assertEquals(5, frs.size());
    }
}