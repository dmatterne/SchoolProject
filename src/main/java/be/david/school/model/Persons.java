package be.david.school.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "PERSONS", uniqueConstraints = @UniqueConstraint(name = "pk_persons", columnNames = {"PRS_ID"}))
public class Persons {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRS")
    @SequenceGenerator(name = "SEQ_PRS", sequenceName = "SEQ_PRS", initialValue = 1, allocationSize = 1)
    @Column(name = "PRS_ID", length = 10)
    private int prs_id;
    private String prs_name;
    private LocalDate prs_birthdate;
    @Enumerated(EnumType.STRING)
    private Gender prs_sex;
    @OneToOne
    @JoinColumn(name = "CNT_ID", foreignKey = @ForeignKey(name="rf_cnt_id_prs"))
    private Country cnt_id;
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