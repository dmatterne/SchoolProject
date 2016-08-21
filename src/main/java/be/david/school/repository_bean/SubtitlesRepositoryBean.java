package be.david.school.repository_bean;

import be.david.school.dbmodel.Subtitles;
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
