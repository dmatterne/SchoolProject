package be.david.school.model;

/**
 * Created by David on 15/08/2016.
 */
public class Persons {
}

//
//    CREATE TABLE PERSONS
//        (prs_id		 	NUMBER(10)	CONSTRAINT pr_persons 		PRIMARY KEY
//,prs_name	 	VARCHAR2(30)	CONSTRAINT nn_prs_name		NOT NULL
//        ,prs_birthdate   	DATE		CONSTRAINT nn_prs_birthdate	NOT NULL
//        ,prs_sex	 	VARCHAR2(1)	CONSTRAINT nn_prs_sex 		NOT NULL
//        CHECK(prs_sex = 'M' OR prs_sex = 'V')
//        ,cnt_id		 	NUMBER(3)	CONSTRAINT rf_cnt_id3		REFERENCES COUNTRIES
//        )
//        /
//
/