package be.david.school.dbmodel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by David on 21/08/2016.
 */
@Embeddable
public class TrailersId implements Serializable{

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRL")
    @SequenceGenerator(sequenceName = "SEQ_TRL",name = "SEQ_TRL",initialValue = 1, allocationSize = 1)
    @Column(name = "TRL_ID", length = 2)
    private int trl_id;

    @OneToOne
    @JoinColumn(name = "FLI_ID", referencedColumnName = "FLI_ID",foreignKey = @ForeignKey(name="rf_fli_id_trl"))
    private Flm_info fli_id;

    public TrailersId() {
    }

    public TrailersId(Flm_info fli_id) {
        this.fli_id = fli_id;
    }

    public int getTrl_id() {
        return trl_id;
    }

    public void setTrl_id(int trl_id) {
        this.trl_id = trl_id;
    }

    public Flm_info getFli_id() {
        return fli_id;
    }

    public void setFli_id(Flm_info fli_id) {
        this.fli_id = fli_id;
    }
}
