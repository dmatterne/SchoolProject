package be.david.school.repository_bean;

import be.david.school.dbmodel.Trailers;
import be.david.school.repository.TrailersRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class TrailersRepositoryBean implements TrailersRepository {

    private EntityManager em;

    public TrailersRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Trailers> findAllTrailers() {
        return em.createQuery("select t from Trailers t", Trailers.class).getResultList();
    }
}
