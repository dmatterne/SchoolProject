package be.david.school.dbmodel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by David on 21/08/2016.
 */
@Embeddable
public class PhotosId implements Serializable {


    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PHT")
    @SequenceGenerator(name = "SEQ_PHT", sequenceName = "SEQ_PHT", initialValue = 1, allocationSize = 1)
    @Column(name = "PHT_ID", length = 2)
    private int pht_id;
    @OneToOne
    @JoinColumn(name = "FLI_ID", foreignKey = @ForeignKey(name="rf_fli_id_pht"))
    private Flm_info fli_id;

    public PhotosId() {
    }

    public PhotosId(Flm_info fli_id) {
        this.fli_id = fli_id;
    }

    public int getPht_id() {
        return pht_id;
    }

    public void setPht_id(int pht_id) {
        this.pht_id = pht_id;
    }

    public Flm_info getFli_id() {
        return fli_id;
    }

    public void setFli_id(Flm_info fli_id) {
        this.fli_id = fli_id;
    }
}
