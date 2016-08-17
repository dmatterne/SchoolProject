package be.david.school;

import be.david.school.bean.CountryRepositoryBean;
import be.david.school.bean.ScoresRepositoryBean;
import be.david.school.model.Country;
import be.david.school.model.Scores;
import be.david.school.repository.CountryRepository;
import be.david.school.repository.ScoresRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class ScoresPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllScores() {
        ScoresRepository sr = new ScoresRepositoryBean(entityManager());
        List<Scores> srs = sr.findAllScores();
        assertEquals(5, srs.size());
    }
//    @Test
//    public void bookCanBePersisted() throws Exception {
//        Collection a = new ArrayList<String>();
//        a.add("George Orwell");
//        Book book = new Book("Animal Farm", a, Book.Genre.thriller);
//        entityManager().persist(book);
//        assertNotNull(book.getId());
//    }
//
//    @Test(expected = PersistenceException.class)
//    public void bookCanNotBePersistedWithoutTitle() throws Exception {
//        Collection a = new ArrayList<String>();
//        a.add("George Orwell");
//        Book book = new Book(null, a, Book.Genre.thriller);
//        entityManager().persist(book);
//    }
//
//    @Test
//    public void bookCanBeRetrievedById() throws Exception {
//        assertEquals("Nineteen Eighty Four", entityManager().find(Book.class, 1000).getTitle());
//    }
}
