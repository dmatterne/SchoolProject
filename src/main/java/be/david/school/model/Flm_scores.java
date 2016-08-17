package be.david.school.model;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "FLM_SCORES", uniqueConstraints = @UniqueConstraint(name = "pk_flm_scores", columnNames = {"FLS_ID"}))
public class Flm_scores {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FLS")
    @SequenceGenerator(name = "SEQ_FLS", sequenceName = "SEQ_FLS", initialValue = 1, allocationSize = 1)
    @Column(name = "FLS_ID", length = 11)
    private int fls_id;

    @OneToOne
    @JoinColumn(name = "SCR_ID", foreignKey = @ForeignKey(name="rf_scr_id_fls"))
    private Scores scr_id;

    private String fls_name;
    @Enumerated(EnumType.STRING)
    private YesNo fls_public;
    @OneToOne
    @JoinColumn(name = "CNT_ID", foreignKey = @ForeignKey(name="rf_fli_id_fls"))
    private Flm_info fli_id;

}


//    CREATE TABLE FLM_SCORES
//        (fls_id			NUMBER(11)	CONSTRAINT pr_flm_scores	PRIMARY KEY
//                        ,scr_id			NUMBER(3)	CONSTRAINT rf_scr_id		REFERENCES SCORES
//                                ,fls_name		VARCHAR2(30)	CONSTRAINT nn_fls_name		NOT NULL
//                                ,fls_public		VARCHAR2(3)	CONSTRAINT nn_fls_public	NOT NULL
//                                CHECK(fls_public = 'YES' OR fls_public = 'NO')
//                                ,fli_id			NUMBER(15)	CONSTRAINT rf_fli_id4		REFERENCES FLM_INFO
//                                )
//                                /

