package be.david.school.model;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "ROLES", uniqueConstraints = @UniqueConstraint(name = "pk_roles", columnNames = {"RLS_ID"}))
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RLS")
    @SequenceGenerator(name = "SEQ_RLS", sequenceName = "SEQ_RLS", initialValue = 1, allocationSize = 1)
    @Column(name = "RLS_ID", length = 5)
    private int rls_id;
    private String rls_description;

}

//        CREATE TABLE ROLES
//        (rls_id 	 	NUMBER(5)	CONSTRAINT pr_roles		PRIMARY KEY
//        ,rls_description 	VARCHAR2(15)	CONSTRAINT nn_rls_description	NOT NULL
//        )
//        /
//