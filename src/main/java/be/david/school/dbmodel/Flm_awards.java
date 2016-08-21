package be.david.school.dbmodel;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "FLM_AWARDS", uniqueConstraints = @UniqueConstraint(name = "pr_flm_awards", columnNames = {"FLA_ID"}))
public class Flm_awards {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FLA")
    @SequenceGenerator(name = "SEQ_FLA", sequenceName = "SEQ_FLA", initialValue = 1, allocationSize = 1)
    @Column(name = "FLA_ID")
    private int fla_id;

    @Column(name = "FLA_NAME", nullable = false, length = 15)
    private String fla_name;

    @OneToOne
    @JoinColumn(name = "FLI_ID", referencedColumnName = "FLI_ID", foreignKey = @ForeignKey(name="rf_fli_id_fla"))
    private Flm_info fli_id;

    public Flm_awards() {
    }

    public Flm_awards(int fla_id, String fla_name, Flm_info fli_id) {
        this.fla_id = fla_id;
        this.fla_name = fla_name;
        this.fli_id = fli_id;
    }

    public int getFla_id() {
        return fla_id;
    }

    public void setFla_id(int fla_id) {
        this.fla_id = fla_id;
    }

    public String getFla_name() {
        return fla_name;
    }

    public void setFla_name(String fla_name) {
        this.fla_name = fla_name;
    }

    public Flm_info getFli_id() {
        return fli_id;
    }

    public void setFli_id(Flm_info fli_id) {
        this.fli_id = fli_id;
    }
}

