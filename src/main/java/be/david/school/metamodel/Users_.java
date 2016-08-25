package be.david.school.metamodel;

import be.david.school.dbmodel.Users;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Created by stannisbaratheon on 25/08/16.
 */
@StaticMetamodel(Users.class)
public class Users_ {
    public static volatile SingularAttribute<Users, Integer> usr_id;
}
