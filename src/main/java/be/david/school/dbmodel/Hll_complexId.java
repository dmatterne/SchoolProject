package be.david.school.dbmodel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by stannisbaratheon on 17/08/16.
 */
@Embeddable
public class Hll_complexId implements Serializable{

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_HLC")
    @SequenceGenerator(name="SEQ_HLC", sequenceName = "SEQ_HLC", initialValue = 1, allocationSize = 1)
    @Column(name = "HLC_ID", length = 3)
    private int hlc_id;

    @OneToOne
    @JoinColumn(name = "CMF_ID", foreignKey = @ForeignKey(name="rf_cmf_id_hlc"))
    private Cmp_features cmf_id;

    public int getHlc_id() {
        return hlc_id;
    }

    public void setHlc_id(int hlc_id) {
        this.hlc_id = hlc_id;
    }

    public Cmp_features getCmf_id() {
        return cmf_id;
    }

    public void setCmf_id(Cmp_features cmf_id) {
        this.cmf_id = cmf_id;
    }
}
