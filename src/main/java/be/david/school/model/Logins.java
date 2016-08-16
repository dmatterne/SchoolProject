package be.david.school.model;

import javax.persistence.Entity;

/**
 * Created by David on 15/08/2016.
 */
@Entity
public class Logins {
}

//    CREATE TABLE LOGINS
//        (lgn_username		VARCHAR2(15)    CONSTRAINT nn_lgn_username      NOT NULL
//                                                                ,lgn_password		VARCHAR2(15)	CONSTRAINT nn_lgn_password	NOT NULL
//                                                                        ,usr_id			NUMBER(15)	CONSTRAINT rf_lgn_users		REFERENCES USERS(usr_id)
//                                                                        ,lgn_function		VARCHAR2(70)    CONSTRAINT nn_lgn_function      NOT NULL
//                                                                        ,cnt_id                 NUMBER(3)	CONSTRAINT rf_lgn_countries	REFERENCES COUNTRIES(cnt_id)
//                                                                        ,lgn_address            VARCHAR2(40)    CONSTRAINT nn_lgn_address       NOT NULL
//                                                                        ,lgn_birthday           DATE            CONSTRAINT nn_lgn_birthdate     NOT NULL
//                                                                        ,lgn_email              VARCHAR2(30)    CONSTRAINT nn_lgn_email         NOT NULL
//                                                                        ,lgn_id                 NUMBER(38)      CONSTRAINT pk_logins            PRIMARY KEY
//                                                                        ,lgn_name               VARCHAR2(15)    CONSTRAINT nn_lgn_name          NOT NULL
//                                                                        ,lgn_sex                VARCHAR2(1)     CONSTRAINT nn_lgn_sex           NOT NULL
//                                                                        ,lgn_tel                NUMBER(15)      CONSTRAINT nn_lgn_tel           NOT NULL
//                                                                        )
//                                                                        /