package be.david.school.repository_bean;

import be.david.school.dbmodel.Languages;
import be.david.school.repository.LanguagesRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class LanguagesRepositoryBean implements LanguagesRepository {

    private EntityManager em;

    public LanguagesRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Languages> findAllLanguages() {
        return em.createQuery("select l from Languages l", Languages.class).getResultList();
    }
}
