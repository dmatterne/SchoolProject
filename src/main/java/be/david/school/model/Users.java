package be.david.school.model;

/**
 * Created by David on 17/08/2016.
 */
public class Users {
}

//    CREATE TABLE USERS
//        (usr_id                       NUMBER(38)      CONSTRAINT pk_users               PRIMARY KEY
//,usr_name                     VARCHAR2(50)    CONSTRAINT nn_usr_name            NOT NULL
//        ,usr_address                  VARCHAR2(40)    CONSTRAINT nn_usr_address         NOT NULL
//        ,usr_birthday                 DATE            CONSTRAINT nn_usr_birthdate       NOT NULL
//        ,usr_email                    VARCHAR2(30)    CONSTRAINT nn_usr_email           NOT NULL
//        ,usr_function                 VARCHAR2(30)    CONSTRAINT nn_usr_function        NOT NULL
//        ,usr_password                 VARCHAR2(100)    CONSTRAINT nn_usr_password        NOT NULL
//        ,usr_sex                      VARCHAR2(1)     CONSTRAINT nn_usr_sex             NOT NULL
//        CHECK(usr_sex = 'M' OR usr_sex = 'V')
//        ,usr_tel                      NUMBER(15)      CONSTRAINT nn_usr_tel             NOT NULL
//        ,usr_username                 VARCHAR2(15)    CONSTRAINT nn_usr_username        NOT NULL
//        CONSTRAINT uq_usr_username        UNIQUE
//        ,cnt_id                       NUMBER(3)       CONSTRAINT rf_usr_countries       REFERENCES COUNTRIES(cnt_id)
//        )
//        /