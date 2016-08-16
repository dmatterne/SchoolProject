package be.david.school.model;

import javax.persistence.Entity;

/**
 * Created by David on 15/08/2016.
 */
@Entity
public class Tickets {
}

//    CREATE TABLE TICKETS
//        (tck_id				NUMBER(38)	CONSTRAINT pr_tickets 		PRIMARY KEY
//        ,prg_id				NUMBER(30)	CONSTRAINT rf_tickets_prg_id	REFERENCES PROGRAMMING
//                ,cst_id				NUMBER(30)	CONSTRAINT rf_tickets_cst_id	REFERENCES CUSTOMERS
//                ,dsc_id				NUMBER(3)	CONSTRAINT rf_tickets_dsc_id	REFERENCES DISCOUNTS
//                ,tck_price			NUMBER(3,2)	CONSTRAINT nn_tck_price		NOT NULL
//                ,usr_id			        NUMBER(15)	CONSTRAINT rf_tck_users		REFERENCES USERS(usr_id)
//                )
//                /


