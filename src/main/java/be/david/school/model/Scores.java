package be.david.school.model;

import javax.persistence.Entity;

/**
 * Created by David on 15/08/2016.
 */
@Entity
public class Scores {
}


//        CREATE TABLE SCORES
//        (scr_id		 	NUMBER(3)	CONSTRAINT pr_scores		PRIMARY KEY
//        ,scr_description 	VARCHAR2(50)	CONSTRAINT nn_scr_description	NOT NULL
//        CONSTRAINT u_scr_description	UNIQUE
//        )
//        /