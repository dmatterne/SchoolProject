package be.david.school.repository_bean;

import be.david.school.dbmodel.Photos;
import be.david.school.repository.PhotosRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class PhotosRepositoryBean implements PhotosRepository {

    private EntityManager em;

    public PhotosRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Photos> findAllPhotos() {
        return em.createQuery("select p from Photos p", Photos.class).getResultList();
    }
}
