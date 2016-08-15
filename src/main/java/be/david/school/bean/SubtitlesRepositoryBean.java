package be.david.school.bean;

import be.david.school.model.Flm_ratings;
import be.david.school.model.Subtitles;
import be.david.school.repository.Flm_ratingsRepository;
import be.david.school.repository.SubtitlesRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class SubtitlesRepositoryBean implements SubtitlesRepository {

    private EntityManager em;

    public SubtitlesRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Subtitles> findAllSubtitles() {
        return em.createQuery("select s from Subtitles s", Subtitles.class).getResultList();
    }
}
