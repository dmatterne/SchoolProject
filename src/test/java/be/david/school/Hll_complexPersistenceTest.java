package be.david.school;

import be.david.school.bean.CountryRepositoryBean;
import be.david.school.bean.Hll_complexRepositoryBean;
import be.david.school.model.Country;
import be.david.school.model.Hll_complex;
import be.david.school.repository.CountryRepository;
import be.david.school.repository.Hll_complexRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class Hll_complexPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllHll_complex() {
        Hll_complexRepository hr = new Hll_complexRepositoryBean(entityManager());
        List<Hll_complex> hrs = hr.findAllHll_complex();
        assertEquals(3, hrs.size());
    }
}
