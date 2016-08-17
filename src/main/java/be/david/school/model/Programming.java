package be.david.school.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "PROGRAMMING", uniqueConstraints = @UniqueConstraint(name = "pk_programming", columnNames = {"PRG_ID"}))
public class Programming {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRG")
    @SequenceGenerator(name = "SEQ_PRG", sequenceName = "SEQ_PRG", initialValue = 1, allocationSize = 1)
    @Column(name = "PRG_ID", length = 30)
    private int prg_id;
    @OneToOne
    @JoinColumn(name = "CMF_ID", foreignKey = @ForeignKey(name="rf_cmf_id_prg"))
    private Cmp_features cmf_id;
    @OneToOne
    @JoinColumn(name = "HLC_ID", foreignKey = @ForeignKey(name="rf_hlc_id_prg"))
    private Hll_complex hlc_id;
    @OneToOne
    @JoinColumn(name = "FLI_ID", foreignKey = @ForeignKey(name="rf_fli_id_prg"))
    private Flm_info fli_id;

    @Column(name = "PRG_DATETIME", nullable = false)
    private LocalDateTime prg_datetime;

    @Column(name = "PRG_PRICE", nullable = false, precision = 6, scale = 2)
    private double prg_price;
}

//    CREATE TABLE PROGRAMMING
//        (prg_id				NUMBER(30)	CONSTRAINT pr_programming 	PRIMARY KEY
//,cmf_id				NUMBER(3)
//        ,hlc_id				NUMBER(3)
//        -- fli id instead of ntf id
//        --,ntf_id				NUMBER(11)	CONSTRAINT rf_prgramming_ntn_id REFERENCES NTN_FILMS
//        ,fli_id                         NUMBER(15)      CONSTRAINT rf_prgramming_fli_id REFERENCES FLM_INFO (fli_id)
//        ,prg_datetime			DATE		CONSTRAINT nn_prg_datetime	NOT NULL
//        ,prg_price			NUMBER(4,2)	CONSTRAINT nn_prg_price		NOT NULL
//        ,CONSTRAINT fk_prg_hlc FOREIGN KEY(hlc_id,cmf_id) REFERENCES HLL_COMPLEX(hlc_id,cmf_id)
//        )
//        /


