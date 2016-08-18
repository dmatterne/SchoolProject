package be.david.school;

import be.david.school.repository_bean.PhotosRepositoryBean;
import be.david.school.model.Photos;
import be.david.school.repository.PhotosRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class PhotosPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllPhotos() {
        PhotosRepository pr = new PhotosRepositoryBean(entityManager());
        List<Photos> prs = pr.findAllPhotos();
        assertEquals(3, prs.size());
    }
}
