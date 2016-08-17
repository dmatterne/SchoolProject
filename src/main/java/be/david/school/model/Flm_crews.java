package be.david.school.model;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "FLM_CREWS", uniqueConstraints = @UniqueConstraint(name = "pk_flm_crews", columnNames = {"RLS_ID", "FLI_ID", "PRS_ID"}))
public class Flm_crews {

    @OneToOne
    @JoinColumn(name = "RLS_ID", foreignKey = @ForeignKey(name="rf_rls_id_flc"))
    private Roles rls_id;
    @OneToOne
    @JoinColumn(name = "FLI_ID", foreignKey = @ForeignKey(name="rf_fli_id_flc"))
    private Flm_info fli_id;
    @OneToOne
    @JoinColumn(name = "PRS_ID", foreignKey = @ForeignKey(name="rf_prs_id_flc"))
    private Persons prs_id;
    private String flc_character;
    private String flc_award;
}

//    CREATE TABLE FLM_CREWS
//        (rls_id			NUMBER(5)	CONSTRAINT rf_rls_id		REFERENCES ROLES
//                ,fli_id			NUMBER(15)	CONSTRAINT rf_fli_id2		REFERENCES FLM_INFO
//                        ,prs_id			NUMBER(10)	CONSTRAINT rf_prs_id		REFERENCES PERSONS
//                        ,flc_character		VARCHAR2(10)
//                        ,flc_award		VARCHAR2(20)
//                        ,CONSTRAINT pr_flm_crews PRIMARY KEY(rls_id,fli_id,prs_id)
//                        )
//                        /


