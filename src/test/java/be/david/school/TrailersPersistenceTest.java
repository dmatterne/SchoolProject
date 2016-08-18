package be.david.school;

import be.david.school.bean.CountryRepositoryBean;
import be.david.school.bean.TrailersRepositoryBean;
import be.david.school.model.Country;
import be.david.school.model.Trailers;
import be.david.school.repository.CountryRepository;
import be.david.school.repository.TrailersRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class TrailersPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllTrailers() {
        TrailersRepository tr = new TrailersRepositoryBean(entityManager());
        List<Trailers> trs = tr.findAllTrailers();
        assertEquals(3, trs.size());
    }
}
