package be.david.school;

import be.david.school.bean.CountryRepositoryBean;
import be.david.school.bean.GenresRepositoryBean;
import be.david.school.model.Country;
import be.david.school.model.Genres;
import be.david.school.repository.CountryRepository;
import be.david.school.repository.GenresRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class GenresPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllGenres() {
        GenresRepository ge = new GenresRepositoryBean(entityManager());
        List<Genres> ges = ge.findAllGenres();
        assertEquals(8, ges.size());
    }

}
