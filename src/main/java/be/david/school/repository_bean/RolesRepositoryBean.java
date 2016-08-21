package be.david.school.repository_bean;

import be.david.school.dbmodel.Roles;
import be.david.school.repository.RolesRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class RolesRepositoryBean implements RolesRepository {

    private EntityManager em;

    public RolesRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Roles> findAllRoles() {
        return em.createQuery("select r from Roles r", Roles.class).getResultList();
    }
}
