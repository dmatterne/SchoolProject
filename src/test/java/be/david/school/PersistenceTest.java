package be.david.school;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Prepares a persistence context for testing with JPA.
 * Use {@link #properties()} to configure database properties
 */
public abstract class PersistenceTest {
    public static final String DRIVER = "javax.persistence.jdbc.driver";
    public static final String URL = "javax.persistence.jdbc.url";
    public static final String USER = "javax.persistence.jdbc.user";
    public static final String PASSWORD = "javax.persistence.jdbc.password";
    public static final String DIALECT = "hibernate.dialect";
    public static final String DDL = "hibernate.hbm2ddl.auto";

    private static final Logger logger = LoggerFactory.getLogger(PersistenceTest.class);

    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;
    private EntityTransaction transaction;

    @BeforeClass
    public static void initializeEntityManagerFactory() {
        logger.info("Creating EntityManagerFactory");
        entityManagerFactory = Persistence.createEntityManagerFactory("KevinPu", properties());
    }

    /**
     * Provides connection settings for the database.
     * @return Map of JPA properties.
     */
    protected static Map<String, String> properties() {
        HashMap<String, String> properties = new HashMap<>();
        properties.put(DRIVER, "oracle.jdbc.OracleDriver");
        properties.put(URL, "jdbc:oracle:thin:@localhost:1521:PERSONALDB");
        properties.put(USER, "Kinepolis");
        properties.put(PASSWORD, "kinepolis");
        properties.put(DIALECT, "org.hibernate.dialect.Oracle10gDialect");
        properties.put(DDL, "create");
        return Collections.unmodifiableMap(properties);
    }

    @Before
    public void initialize() {
        logger.info("Creating transacted EntityManager");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    @After
    public void destroy() {
        logger.info("Committing and closing transacted EntityManager");
        if(transaction != null && !transaction.getRollbackOnly()) {
            transaction.commit();
        }

        if(entityManager != null) {
            entityManager.close();
        }
    }

    @AfterClass
    public static void destroyEntityManagerFactory() {
        logger.info("Closing EntityManagerFactory");
        if(entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    /**
     * Obtains the current EntityManager. Use this to write tests against.
     */
    protected EntityManager entityManager() {
        return this.entityManager;
    }

    /**
     * Obtains a <strong>new</strong> JDBC connection using connection settings defined in {@link #properties()}
     * @return A new JDBC connection. Callsite is responsible for closing.
     * @throws SQLException When the shit hits the fan.
     */
    protected Connection newConnection() throws SQLException {
        Map<String, String> properties = properties();
        return DriverManager.getConnection(properties.get(URL), properties.get(USER), properties.get(PASSWORD));
    }
}
