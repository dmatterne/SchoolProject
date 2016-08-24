package be.david.school.html;

import be.david.school.interfaces.DropDownMarker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * Created by David on 21/08/2016.
 */
public class  DropDown<T extends DropDownMarker> {

    private Class<T> typeClass;
    private Collection<String> drop_down;
    private boolean link_mode = false;
    private String name = "";

    public DropDown(boolean link_mode, String name, Class<T> typeClass) {
        this.typeClass = typeClass;
        this.link_mode = link_mode;
        this.name = name;
    }

    @Override
    public String toString() {

//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DavidPu");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();

        String dropdown = "<select name='" + this.name +
                "'>";

//        TypedQuery<T> query = typeClass.getAnnotations().


        dropdown += "</select>";

        return dropdown;
    }
}
