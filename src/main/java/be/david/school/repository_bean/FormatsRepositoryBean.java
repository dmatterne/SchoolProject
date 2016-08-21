package be.david.school.repository_bean;

import be.david.school.dbmodel.Formats;
import be.david.school.repository.FormatsRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class FormatsRepositoryBean implements FormatsRepository {

    private EntityManager em;

    public FormatsRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Formats> findAllFormats() {
        return em.createQuery("select f from Formats f", Formats.class).getResultList();
    }
}
