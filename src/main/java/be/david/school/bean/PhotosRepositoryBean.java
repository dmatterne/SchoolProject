package be.david.school.bean;

import be.david.school.model.Customers;
import be.david.school.model.Photos;
import be.david.school.repository.CustomersRepository;
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
