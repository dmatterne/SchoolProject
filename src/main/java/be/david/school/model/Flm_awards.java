package be.david.school.model;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "FLM_AWARDS", uniqueConstraints = @UniqueConstraint(name = "pr_flm_awards", columnNames = {"FLA_ID"}))
public class Flm_awards {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FLA")
    @SequenceGenerator(name = "SEQ_FLA", sequenceName = "SEQ_FLA", initialValue = 1, allocationSize = 1)
    @Column(name = "FLA_ID", )
    private int fla_id;

    @Column(name = "FLA_NAME", nullable = false, length = 15)
    private String fla_name;

    @OneToOne
    @JoinColumn(name = "FLI_ID", referencedColumnName = "FLI_ID")
    private int fli_id;

}
//
//    CREATE TABLE FLM_AWARDS
//        (fla_id			NUMBER(11)	CONSTRAINT pr_flm_awards	PRIMARY KEY
//                                                        ,fla_name		VARCHAR2(15)	CONSTRAINT nn_fla_name		NOT NULL
//                                                                ,fli_id			NUMBER(15)	CONSTRAINT rf_fli_id7		REFERENCES FLM_INFO
//                                                                )
//                                                                /

