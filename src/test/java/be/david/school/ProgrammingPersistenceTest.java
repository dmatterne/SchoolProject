package be.david.school;

import be.david.school.repository_bean.ProgrammingRepositoryBean;
import be.david.school.dbmodel.Programming;
import be.david.school.repository.ProgrammingRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class ProgrammingPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllProgramming() {
        ProgrammingRepository pr = new ProgrammingRepositoryBean(entityManager());
        List<Programming> prs = pr.findAllProgramming();
        assertEquals(3, prs.size());
    }
}
