package be.david.school.model;

import javax.persistence.Entity;

/**
 * Created by David on 15/08/2016.
 */
@Entity
public class Programming {
}

//    CREATE TABLE PROGRAMMING
//        (prg_id				NUMBER(30)	CONSTRAINT pr_programming 	PRIMARY KEY
//,cmf_id				NUMBER(3)
//        ,hlc_id				NUMBER(3)
//        -- fli id instead of ntf id
//        --,ntf_id				NUMBER(11)	CONSTRAINT rf_prgramming_ntn_id REFERENCES NTN_FILMS
//        ,fli_id                         NUMBER(15)      CONSTRAINT rf_prgramming_fli_id REFERENCES FLM_INFO (fli_id)
//        ,prg_datetime			DATE		CONSTRAINT nn_prg_datetime	NOT NULL
//        ,prg_price			NUMBER(4,2)	CONSTRAINT nn_prg_price		NOT NULL
//        ,CONSTRAINT fk_prg_hlc FOREIGN KEY(hlc_id,cmf_id) REFERENCES HLL_COMPLEX(hlc_id,cmf_id)
//        )
//        /


