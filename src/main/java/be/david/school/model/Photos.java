package be.david.school.model;

import javax.persistence.Entity;

/**
 * Created by David on 17/08/2016.
 */
@Entity
public class Photos {
}

//    CREATE TABLE PHOTOS
//        (pht_id			NUMBER(2)
//                                                ,fli_id			NUMBER(15)	CONSTRAINT rf_fli_id6		REFERENCES FLM_INFO
//                                                        ,pht_photo		BLOB		CONSTRAINT nn_pht_photo		NOT NULL
//                                                        ,pht_contenttype        VARCHAR2(50)    CONSTRAINT nn_pht_content       NOT NULL
//                                                        ,pht_length             NUMBER(15)      CONSTRAINT nn_pht_length        NOT NULL
//                                                        ,pht_name               VARCHAR2(50)    CONSTRAINT nn_pht_name          NOT NULL
//                                                        ,CONSTRAINT pr_photos PRIMARY KEY(pht_id,fli_id)
//                                                        )
//                                                        /
//
//