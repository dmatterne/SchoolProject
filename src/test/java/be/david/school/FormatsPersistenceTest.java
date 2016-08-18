package be.david.school;

import be.david.school.repository_bean.FormatsRepositoryBean;
import be.david.school.model.Formats;
import be.david.school.repository.FormatsRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class FormatsPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllFormats() {
        FormatsRepository fo = new FormatsRepositoryBean(entityManager());
        List<Formats> fors = fo.findAllFormats();
        assertEquals(3, fors.size());
    }
}
