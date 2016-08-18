package be.david.school;

import be.david.school.repository_bean.DistributorsRepositoryBean;
import be.david.school.model.Distributors;
import be.david.school.repository.DistributorsRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class DistributorsPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllDistributors() {
        DistributorsRepository dis = new DistributorsRepositoryBean(entityManager());
        List<Distributors> dists = dis.findAllDistributors();
        assertEquals(3, dists.size());
    }
}
