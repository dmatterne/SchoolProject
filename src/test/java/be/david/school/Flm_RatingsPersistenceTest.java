package be.david.school;

import be.david.school.repository_bean.Flm_ratingsRepositoryBean;
import be.david.school.dbmodel.Flm_ratings;
import be.david.school.repository.Flm_ratingsRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class Flm_RatingsPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllFRA() {
        Flm_ratingsRepository frr = new Flm_ratingsRepositoryBean(entityManager());
        List<Flm_ratings> ratings = frr.findAllFLMRatings();
        assertEquals(4, ratings.size());
    }
}
