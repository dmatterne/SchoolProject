package be.david.school.dbmodel;

import be.david.school.Enums.YesNo;

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

    @Column(name = "FLS_NAME", length = 30, nullable = false)
    private String fls_name;
    @Enumerated(EnumType.STRING)
    @Column(name = "FLS_PUBLIC", length = 3, nullable = false)
    private YesNo fls_public;

    @OneToOne
    @JoinColumn(name = "FLI_ID", foreignKey = @ForeignKey(name="rf_fli_id_fls"))
    private Flm_info fli_id;


    public Flm_scores() {
    }

    public Flm_scores(Scores scr_id, String fls_name, YesNo fls_public, Flm_info fli_id) {
        this.scr_id = scr_id;
        this.fls_name = fls_name;
        this.fls_public = fls_public;
        this.fli_id = fli_id;
    }

    public int getFls_id() {
        return fls_id;
    }

    public void setFls_id(int fls_id) {
        this.fls_id = fls_id;
    }

    public Scores getScr_id() {
        return scr_id;
    }

    public void setScr_id(Scores scr_id) {
        this.scr_id = scr_id;
    }

    public String getFls_name() {
        return fls_name;
    }

    public void setFls_name(String fls_name) {
        this.fls_name = fls_name;
    }

    public YesNo getFls_public() {
        return fls_public;
    }

    public void setFls_public(YesNo fls_public) {
        this.fls_public = fls_public;
    }

    public Flm_info getFli_id() {
        return fli_id;
    }

    public void setFli_id(Flm_info fli_id) {
        this.fli_id = fli_id;
    }
}


