package be.david.school.repository_bean;

import be.david.school.dbmodel.Sounds;
import be.david.school.repository.SoundsRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class SoundsRepositoryBean implements SoundsRepository {

    private EntityManager em;

    public SoundsRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Sounds> findAllSounds() {
        return em.createQuery("select s from Sounds s", Sounds.class).getResultList();
    }
}
