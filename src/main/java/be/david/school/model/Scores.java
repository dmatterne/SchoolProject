package be.david.school.model;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "SCORES", uniqueConstraints = @UniqueConstraint(name = "pk_scores", columnNames = {"SCR_ID"}))
public class Scores {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SCR")
    @SequenceGenerator(name = "SEQ_SCR", sequenceName = "SEQ_SCR", initialValue = 1, allocationSize = 1)
    @Column(name = "SCR_ID", length = 3)
    private int scr_id;
    private String scr_description;

}


//        CREATE TABLE SCORES
//        (scr_id		 	NUMBER(3)	CONSTRAINT pr_scores		PRIMARY KEY
//        ,scr_description 	VARCHAR2(50)	CONSTRAINT nn_scr_description	NOT NULL
//        CONSTRAINT u_scr_description	UNIQUE
//        )
//        /