package be.david.school.model;

import javax.persistence.Entity;

/**
 * Created by David on 15/08/2016.
 */
@Entity
public class Flm_reviews {
}

//    CREATE TABLE FLM_REVIEWS
//        (fre_id			NUMBER(10)	CONSTRAINT pr_flm_reviews	PRIMARY KEY
//                                ,fre_title		VARCHAR2(40)	CONSTRAINT nn_fre_title		NOT NULL
//                                        ,fre_content		LONG		CONSTRAINT nn_fre_content	NOT NULL
//                                        ,fre_name		VARCHAR2(30)	CONSTRAINT nn_fre_name		NOT NULL
//                                        ,fre_public		VARCHAR2(3)	CONSTRAINT nn_fre_public	NOT NULL
//                                        CHECK(fre_public = 'YES' OR fre_public = 'NO')
//                                        ,lng_id			NUMBER(3)	CONSTRAINT rf_lng_id2		REFERENCES LANGUAGES
//                                        ,fls_id			NUMBER(11)	CONSTRAINT rf_fls_id3		REFERENCES FLM_SCORES
//                                        )
//                                        /

