package be.david.school;

import be.david.school.bean.Flm_ratingsRepositoryBean;
import be.david.school.bean.SubtitlesRepositoryBean;
import be.david.school.model.Flm_ratings;
import be.david.school.model.Subtitles;
import be.david.school.repository.Flm_ratingsRepository;
import be.david.school.repository.SubtitlesRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class SubtitlesPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllSubtitles() {
        SubtitlesRepository sub = new SubtitlesRepositoryBean(entityManager());
        List<Subtitles> subs = sub.findAllSubtitles();
        assertEquals(3, subs.size());
    }
}
