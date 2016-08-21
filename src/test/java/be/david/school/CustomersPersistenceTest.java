package be.david.school;

import be.david.school.repository_bean.CustomersRepositoryBean;
import be.david.school.dbmodel.Customers;
import be.david.school.repository.CustomersRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class CustomersPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllCountries() {
        CustomersRepository cr = new CustomersRepositoryBean(entityManager());
        List<Customers> crs = cr.findAllCustomers();
        assertEquals(3, crs.size());
    }

}
