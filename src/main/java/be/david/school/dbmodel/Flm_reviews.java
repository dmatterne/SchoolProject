package be.david.school.dbmodel;

import be.david.school.Enums.YesNo;
import be.david.school.interfaces.DropDownMarker;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "FLM_REVIEWS", uniqueConstraints = @UniqueConstraint(name = "pk_flm_reviews", columnNames = {"FRE_ID"}))
public class Flm_reviews implements DropDownMarker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FRE")
    @SequenceGenerator(name = "SEQ_FRE", sequenceName = "SEQ_FRE", initialValue = 1, allocationSize = 1)
    @Column(name = "FRE_ID", length = 10, nullable = false)
    private int fre_id;
    @Column(name = "FRE_TITLE", length = 40, nullable = false)
    private String fre_title;
    @Column(name = "FRE_CONTENT", nullable = false)
    private String fre_content;
    @Column(name = "FRE_NAME", length = 30, nullable = false)
    private String fre_name;
    @Enumerated(EnumType.STRING)
    @Column(name = "FRE_PUBLIC", length = 3, nullable = false)
    private YesNo fre_public;

    @OneToOne
    @JoinColumn(name = "LNG_ID", foreignKey = @ForeignKey(name="rf_lng_id_fre"))
    private Languages lng_id;

    @OneToOne
    @JoinColumn(name = "FLS_ID", foreignKey = @ForeignKey(name="rf_fls_id_fre"))
    private Flm_scores fls_id;

    public Flm_reviews() {
    }

    public Flm_reviews(String fre_title, String fre_content, String fre_name, YesNo fre_public, Languages lng_id, Flm_scores fls_id) {
        this.fre_title = fre_title;
        this.fre_content = fre_content;
        this.fre_name = fre_name;
        this.fre_public = fre_public;
        this.lng_id = lng_id;
        this.fls_id = fls_id;
    }

    public int getFre_id() {
        return fre_id;
    }

    public void setFre_id(int fre_id) {
        this.fre_id = fre_id;
    }

    public String getFre_title() {
        return fre_title;
    }

    public void setFre_title(String fre_title) {
        this.fre_title = fre_title;
    }

    public String getFre_content() {
        return fre_content;
    }

    public void setFre_content(String fre_content) {
        this.fre_content = fre_content;
    }

    public String getFre_name() {
        return fre_name;
    }

    public void setFre_name(String fre_name) {
        this.fre_name = fre_name;
    }

    public YesNo getFre_public() {
        return fre_public;
    }

    public void setFre_public(YesNo fre_public) {
        this.fre_public = fre_public;
    }

    public Languages getLng_id() {
        return lng_id;
    }

    public void setLng_id(Languages lng_id) {
        this.lng_id = lng_id;
    }

    public Flm_scores getFls_id() {
        return fls_id;
    }

    public void setFls_id(Flm_scores fls_id) {
        this.fls_id = fls_id;
    }
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

