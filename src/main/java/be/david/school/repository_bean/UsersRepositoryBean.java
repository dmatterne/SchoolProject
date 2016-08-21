package be.david.school.repository_bean;

import be.david.school.dbmodel.Users;
import be.david.school.repository.UsersRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class UsersRepositoryBean implements UsersRepository {

    private EntityManager em;

    public UsersRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Users> findAllUsers() {
        return em.createQuery("select u from Users u", Users.class).getResultList();
    }
}
