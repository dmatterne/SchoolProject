package be.david.school.bean;

import be.david.school.model.Flm_ratings;
import be.david.school.model.Sounds;
import be.david.school.repository.Flm_ratingsRepository;
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
