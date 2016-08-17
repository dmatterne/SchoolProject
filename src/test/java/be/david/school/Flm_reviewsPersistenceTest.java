package be.david.school;

import be.david.school.bean.CountryRepositoryBean;
import be.david.school.bean.Flm_reviewsRepositoryBean;
import be.david.school.model.Country;
import be.david.school.model.Flm_reviews;
import be.david.school.repository.CountryRepository;
import be.david.school.repository.Flm_reviewsRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class Flm_reviewsPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllFlm_reviews() {
        Flm_reviewsRepository fr = new Flm_reviewsRepositoryBean(entityManager());
        List<Flm_reviews> frs = fr.findAllFlm_reviews();
        assertEquals(5, frs.size());
    }
}
