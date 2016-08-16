package be.david.school.model;

import javax.persistence.Entity;

/**
 * Created by David on 15/08/2016.
 */
@Entity
public class Flm_crews {
}

//    CREATE TABLE FLM_CREWS
//        (rls_id			NUMBER(5)	CONSTRAINT rf_rls_id		REFERENCES ROLES
//                ,fli_id			NUMBER(15)	CONSTRAINT rf_fli_id2		REFERENCES FLM_INFO
//                        ,prs_id			NUMBER(10)	CONSTRAINT rf_prs_id		REFERENCES PERSONS
//                        ,flc_character		VARCHAR2(10)
//                        ,flc_award		VARCHAR2(20)
//                        ,CONSTRAINT pr_flm_crews PRIMARY KEY(rls_id,fli_id,prs_id)
//                        )
//                        /


