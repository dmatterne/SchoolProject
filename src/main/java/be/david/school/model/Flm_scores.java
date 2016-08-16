package be.david.school.model;

import javax.persistence.Entity;

/**
 * Created by David on 15/08/2016.
 */
@Entity
public class Flm_scores {
}


//    CREATE TABLE FLM_SCORES
//        (fls_id			NUMBER(11)	CONSTRAINT pr_flm_scores	PRIMARY KEY
//                        ,scr_id			NUMBER(3)	CONSTRAINT rf_scr_id		REFERENCES SCORES
//                                ,fls_name		VARCHAR2(30)	CONSTRAINT nn_fls_name		NOT NULL
//                                ,fls_public		VARCHAR2(3)	CONSTRAINT nn_fls_public	NOT NULL
//                                CHECK(fls_public = 'YES' OR fls_public = 'NO')
//                                ,fli_id			NUMBER(15)	CONSTRAINT rf_fli_id4		REFERENCES FLM_INFO
//                                )
//                                /

