package be.david.school;

import be.david.school.bean.CountryRepositoryBean;
import be.david.school.bean.ScoresRepositoryBean;
import be.david.school.model.Country;
import be.david.school.model.Scores;
import be.david.school.repository.CountryRepository;
import be.david.school.repository.ScoresRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class ScoresPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllScores() {
        ScoresRepository sr = new ScoresRepositoryBean(entityManager());
        List<Scores> srs = sr.findAllScores();
        assertEquals(3, srs.size());
    }

}
