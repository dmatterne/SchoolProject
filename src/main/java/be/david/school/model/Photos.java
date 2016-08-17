package be.david.school.model;

import javax.persistence.*;
import java.sql.Blob;

/**
 * Created by David on 17/08/2016.
 */
@Entity
@Table(name = "PHOTOS", uniqueConstraints = @UniqueConstraint(name = "pk_photos", columnNames = {"PHT_ID", "FLI_ID"}))
public class Photos {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PHT")
    @SequenceGenerator(name = "SEQ_PHT", sequenceName = "SEQ_PHT", initialValue = 1, allocationSize = 1)
    @Column(name = "PHT_ID", length = 2)
     private int pht_id;
    @OneToOne
    @JoinColumn(name = "FLI_ID", foreignKey = @ForeignKey(name="rf_fli_id_pht"))
    private Flm_info fli_id;
    private Blob pht_photo;
    private String pht_contenttype;
    private int pht_length;


}

//    CREATE TABLE PHOTOS
//        (pht_id			NUMBER(2)
//                                                ,fli_id			NUMBER(15)	CONSTRAINT rf_fli_id6		REFERENCES FLM_INFO
//                                                        ,pht_photo		BLOB		CONSTRAINT nn_pht_photo		NOT NULL
//                                                        ,pht_contenttype        VARCHAR2(50)    CONSTRAINT nn_pht_content       NOT NULL
//                                                        ,pht_length             NUMBER(15)      CONSTRAINT nn_pht_length        NOT NULL
//                                                        ,pht_name               VARCHAR2(50)    CONSTRAINT nn_pht_name          NOT NULL
//                                                        ,CONSTRAINT pr_photos PRIMARY KEY(pht_id,fli_id)
//                                                        )
//                                                        /
//
//