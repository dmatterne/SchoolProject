package be.david.school.repository_bean;

import be.david.school.dbmodel.Users;
import be.david.school.metamodel.Users_;
import be.david.school.repository.UsersRepository;
import org.hibernate.Criteria;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public class UsersRepositoryBean implements UsersRepository {

    private EntityManager em;
    private CriteriaBuilder builder;
    private CriteriaQuery<Users> query;
    private Root<Users> u;
    private boolean builderActive;




    public UsersRepositoryBean(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Users> findAllUsers() {
        return em.createQuery("select u from Users u", Users.class).getResultList();
    }

}
