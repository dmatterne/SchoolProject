package be.david.school.model;

/**
 * Created by David on 15/08/2016.
 */
public class Cmp_languages {
}


//    CREATE TABLE CMP_LANGUAGES
//        (cmf_id				NUMBER(3)	CONSTRAINT rf_languages_cmf_id 	REFERENCES CMP_FEATURES
//,lng_id				NUMBER(3)	CONSTRAINT rf_languages_lng_id	REFERENCES LANGUAGES
//        ,CONSTRAINT pr_cmp_languages PRIMARY KEY(cmf_id, lng_id)
//        )
//        /