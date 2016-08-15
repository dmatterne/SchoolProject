package be.david.school;

import be.david.school.bean.SoundsRepositoryBean;
import be.david.school.bean.SubtitlesRepositoryBean;
import be.david.school.model.Sounds;
import be.david.school.model.Subtitles;
import be.david.school.repository.SoundsRepository;
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
public class SoundsPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllSounds() {
        SoundsRepository sound = new SoundsRepositoryBean(entityManager());
        List<Sounds> s = sound.findAllSounds();
        assertEquals(3, s.size());
    }
}
