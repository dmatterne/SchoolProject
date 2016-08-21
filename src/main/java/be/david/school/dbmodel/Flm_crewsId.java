package be.david.school.dbmodel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by stannisbaratheon on 17/08/16.
 */
@Embeddable
public class Flm_crewsId implements Serializable {


    @OneToOne
    @JoinColumn(name = "RLS_ID", foreignKey = @ForeignKey(name="rf_rls_id_flc"))
    private Roles rls_id;

    @OneToOne
    @JoinColumn(name = "FLI_ID", foreignKey = @ForeignKey(name="rf_fli_id_flc"))
    private Flm_info fli_id;

    @OneToOne
    @JoinColumn(name = "PRS_ID", foreignKey = @ForeignKey(name="rf_prs_id_flc"))
    private Persons prs_id;

    public Roles getRls_id() {
        return rls_id;
    }

    public void setRls_id(Roles rls_id) {
        this.rls_id = rls_id;
    }

    public Flm_info getFli_id() {
        return fli_id;
    }

    public void setFli_id(Flm_info fli_id) {
        this.fli_id = fli_id;
    }

    public Persons getPrs_id() {
        return prs_id;
    }

    public void setPrs_id(Persons prs_id) {
        this.prs_id = prs_id;
    }
}
