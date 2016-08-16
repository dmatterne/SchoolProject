package be.david.school;

import be.david.school.bean.Cmp_FeaturesRepositoryBean;
import be.david.school.bean.Lng_versionsRepositoryBean;
import be.david.school.model.Cmp_features;
import be.david.school.model.Lng_versions;
import be.david.school.repository.Cmp_FeaturesRepository;
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
public class Cmp_featuresPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllCmp_features() {
        Cmp_FeaturesRepository cmf = new Cmp_FeaturesRepositoryBean(entityManager());
        List<Cmp_features> cmfs = cmf.findAllCmpFeatures();
        assertEquals(2, cmfs.size());
    }
}


