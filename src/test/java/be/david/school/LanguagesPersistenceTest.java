package be.david.school;

import be.david.school.repository_bean.LanguagesRepositoryBean;
import be.david.school.dbmodel.Languages;
import be.david.school.repository.LanguagesRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class LanguagesPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllLanguages() {
        LanguagesRepository lng = new LanguagesRepositoryBean(entityManager());
        List<Languages> lngs = lng.findAllLanguages();
        assertEquals(6, lngs.size());
    }

}
