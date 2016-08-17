package be.david.school.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "CMP_LANGUAGES" , uniqueConstraints = @UniqueConstraint(name = "pr_cmp_languages", columnNames = {"CMF_ID","LNG_ID"}))
public class Cmp_languages {

}


//    CREATE TABLE CMP_LANGUAGES
//        (cmf_id				NUMBER(3)	CONSTRAINT rf_languages_cmf_id 	REFERENCES CMP_FEATURES
//,lng_id				NUMBER(3)	CONSTRAINT rf_languages_lng_id	REFERENCES LANGUAGES
//        ,CONSTRAINT pr_cmp_languages PRIMARY KEY(cmf_id, lng_id)
//        )
//        /