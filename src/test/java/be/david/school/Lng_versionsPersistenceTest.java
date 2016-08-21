package be.david.school;

import be.david.school.repository_bean.Lng_versionsRepositoryBean;
import be.david.school.dbmodel.Lng_versions;
import be.david.school.repository.Lng_versionsRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class Lng_versionsPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllLng_versions() {
        Lng_versionsRepository lv = new Lng_versionsRepositoryBean(entityManager());
        List<Lng_versions> lvs = lv.findAllLng_versions();
        assertEquals(2, lvs.size());
    }
}
