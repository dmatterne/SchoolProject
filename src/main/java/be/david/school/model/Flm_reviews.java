package be.david.school.model;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "FLM_REVIEWS", uniqueConstraints = @UniqueConstraint(name = "pk_flm_reviews", columnNames = {"FRE_ID"}))
public class Flm_reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FRE")
    @SequenceGenerator(name = "SEQ_FRE", sequenceName = "SEQ_FRE", initialValue = 1, allocationSize = 1)
    @Column(name = "SCR_ID", length = 10)
    private int fre_id;
    private String fre_title;
    private Long fre_content;
    private String fre_name;
    @Enumerated(EnumType.STRING)
    private YesNo fre_public;

    @OneToOne
    @JoinColumn(name = "LNG_ID", foreignKey = @ForeignKey(name="rf_lng_id_fre"))
    private Languages lng_id;

    @OneToOne
    @JoinColumn(name = "FLS_ID", foreignKey = @ForeignKey(name="rf_fls_id_fre"))
    private Flm_scores fls_id;

}

//    CREATE TABLE FLM_REVIEWS
//        (fre_id			NUMBER(10)	CONSTRAINT pr_flm_reviews	PRIMARY KEY
//                                ,fre_title		VARCHAR2(40)	CONSTRAINT nn_fre_title		NOT NULL
//                                        ,fre_content		LONG		CONSTRAINT nn_fre_content	NOT NULL
//                                        ,fre_name		VARCHAR2(30)	CONSTRAINT nn_fre_name		NOT NULL
//                                        ,fre_public		VARCHAR2(3)	CONSTRAINT nn_fre_public	NOT NULL
//                                        CHECK(fre_public = 'YES' OR fre_public = 'NO')
//                                        ,lng_id			NUMBER(3)	CONSTRAINT rf_lng_id2		REFERENCES LANGUAGES
//                                        ,fls_id			NUMBER(11)	CONSTRAINT rf_fls_id3		REFERENCES FLM_SCORES
//                                        )
//                                        /

