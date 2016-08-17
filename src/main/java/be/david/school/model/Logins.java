package be.david.school.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "LOGINS", uniqueConstraints = @UniqueConstraint(name = "pk_logins", columnNames = {"LGN_ID"}))
public class Logins {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LGN")
    @SequenceGenerator(name = "LGN_SCR", sequenceName = "LGN_SCR", initialValue = 1, allocationSize = 1)
    @Column(name = "LGN_ID", length = 38)
    private int lgn_id;

    private String lgn_username;
    private String lgn_password;
    @OneToOne
    @JoinColumn(name = "USR_ID", foreignKey = @ForeignKey(name="rf_usr_id_lgn"))
    private Users usr_id;
    private String lgn_function;

    @OneToOne
    @JoinColumn(name = "CNT_ID", foreignKey = @ForeignKey(name="rf_cnt_id_lgn"))
    private Country cnt_id;
    private String lgn_address;
    private LocalDate lgn_birthdate;
    private String lgn_email;


    private String lgn_name;
    @Enumerated(EnumType.STRING)
    private Gender lgn_sex;
    private int lgn_tel;
}

//    CREATE TABLE LOGINS
//        (lgn_username		VARCHAR2(15)    CONSTRAINT nn_lgn_username      NOT NULL
//                                                                ,lgn_password		VARCHAR2(15)	CONSTRAINT nn_lgn_password	NOT NULL
//                                                                        ,usr_id			NUMBER(15)	CONSTRAINT rf_lgn_users		REFERENCES USERS(usr_id)
//                                                                        ,lgn_function		VARCHAR2(70)    CONSTRAINT nn_lgn_function      NOT NULL
//                                                                        ,cnt_id                 NUMBER(3)	CONSTRAINT rf_lgn_countries	REFERENCES COUNTRIES(cnt_id)
//                                                                        ,lgn_address            VARCHAR2(40)    CONSTRAINT nn_lgn_address       NOT NULL
//                                                                        ,lgn_birthday           DATE            CONSTRAINT nn_lgn_birthdate     NOT NULL
//                                                                        ,lgn_email              VARCHAR2(30)    CONSTRAINT nn_lgn_email         NOT NULL
//                                                                        ,lgn_id                 NUMBER(38)      CONSTRAINT pk_logins            PRIMARY KEY
//                                                                        ,lgn_name               VARCHAR2(15)    CONSTRAINT nn_lgn_name          NOT NULL
//                                                                        ,lgn_sex                VARCHAR2(1)     CONSTRAINT nn_lgn_sex           NOT NULL
//                                                                        ,lgn_tel                NUMBER(15)      CONSTRAINT nn_lgn_tel           NOT NULL
//                                                                        )
//                                                                        /