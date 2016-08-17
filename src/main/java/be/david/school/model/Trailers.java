package be.david.school.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Created by David on 15/08/2016.
 */
@Table(name = "TRAILERS", uniqueConstraints = @UniqueConstraint(name = "pk_trailers", columnNames = {"TRL_ID","FLI_ID"}))
public class Trailers {
}
//
//    CREATE TABLE TRAILERS
//        (trl_id			NUMBER(2)
//                                        ,fli_id			NUMBER(15)	CONSTRAINT rf_fli_id5		REFERENCES FLM_INFO
//                                                ,trl_trailer		BLOB		CONSTRAINT nn_trl_trailer	NOT NULL
//                                                ,trl_length             NUMBER(15)      CONSTRAINT nn_trl_length        NOT NULL
//                                                ,trl_name               VARCHAR2(50)    CONSTRAINT nn_trl_name          NOT NULL
//                                                ,CONSTRAINT pr_trailers PRIMARY KEY(trl_id,fli_id)
//                                                )
//                                                /

