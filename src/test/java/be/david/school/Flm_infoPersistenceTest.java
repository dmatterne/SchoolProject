package be.david.school;

import be.david.school.repository_bean.Flm_infoRepositoryBean;
import be.david.school.model.Flm_info;
import be.david.school.repository.Flm_infoRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class Flm_infoPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();


    private EntityManager em;

    @Test
    public void FindAllFlm_info() {
        Flm_infoRepository fo = new Flm_infoRepositoryBean(entityManager());
        List<Flm_info> fos = fo.findAllFlm_info();
        assertEquals(2, fos.size());
    }
}
