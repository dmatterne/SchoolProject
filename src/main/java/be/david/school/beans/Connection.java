package be.david.school.beans;
import org.hibernate.boot.model.relational.Database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by David on 24/08/2016.
 */
public class Connection {

    private static final String error_line = "\n###########################################################################\n";
    private EntityManagerFactory emf
    private EntityManager em;

    //        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DavidPu");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();

    private boolean connected = false;
    private boolean errors = false;
    private static Logger logger = Logger.getLogger("Database.Connection");
    private static FileHandler fh;

    public Connection() {
        try {
            fh = new FileHandler("kinepolis.log");
            fh.setLevel(Level.ALL);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (Exception var2) {
            logger.info("We could not open the file handler, just using default log");
        }

        logger.info("\n###########################################################################\nInititialising Connection Class\n###########################################################################\n");
    }

//    public boolean closeResultset() {
//        try {
//            logger.log(Level.INFO, "\n###########################################################################\nclosing the connection and cleaning up\n###########################################################################\n");
//            this.resultset.close();
//            this.errors = false;
//        } catch (Exception var4) {
//            logger.info("No resultset to close!");
//        }
//
//        try {
//            this.connection.closeAllObjects();
//            this.connection.close();
//            this.connection.flush();
//            this.connected = false;
//        } catch (Exception var3) {
//            logger.log(Level.WARNING, "Failed to close the connection", var3);
//            this.errors = true;
//            return false;
//        }
//
//        try {
//            fh.flush();
//            logger.removeHandler(fh);
//            fh.close();
//        } catch (Exception var2) {
//            logger.info("failed to close the log file, or log file never opened");
//        }
//
//        return true;
//    }

    public boolean createConnection() {
        boolean check = false;
        if(this.connected) {
            check = true;
        } else {


            try {
                emf = Persistence.createEntityManagerFactory("DavidPu");
                em = emf.createEntityManager();
                em.getTransaction().begin();

                this.connected = true;
                check = true;
            } catch (Exception var5) {
                logger.log(Level.SEVERE, "Failed to connect to Database", var5);
                this.connected = false;
                check = false;
            }
        }

        return check;
    }


    public boolean closeResultset() {


        try {
            em.getTransaction().commit();
            em.close();
            emf.close();
            this.connected = false;
        } catch (Exception var3) {
            logger.log(Level.WARNING, "Failed to close the connection", var3);
            this.errors = true;
            return false;
        }

        try {
            fh.flush();
            logger.removeHandler(fh);
            fh.close();
        } catch (Exception var2) {
            logger.info("failed to close the log file, or log file never opened");
        }

        return true;
    }

    public static String getError_line() {
        return error_line;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isErrors() {
        return errors;
    }

    public void setErrors(boolean errors) {
        this.errors = errors;
    }
}

