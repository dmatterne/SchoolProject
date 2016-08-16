package be.david.school.model;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table( name = "CMP_FEATURES", uniqueConstraints = @UniqueConstraint(name = "pr_cmp_features", columnNames = {"CMF_ID"}))
public class Cmp_features {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CMF")
    @SequenceGenerator(name = "SEQ_CMF", sequenceName = "SEQ_CMF", initialValue = 1, allocationSize = 1)
    @Column(name = "CMF_ID", length = 3)
    private int cmf_id;

    @Column(name = "CMF_NAME", nullable = false, length = 20)
    private String cmf_name;

    @OneToOne
    @JoinColumn(name = "CNT_ID")
    private Country cnt_id;

    @Column(name = "CMF_ADDRESS", nullable = false, length = 50)
    private String cmf_address;

    @Column(name = "CMF_CONSTRUCTIONYEAR", nullable = false, length = 4)
    private int cmf_constructionyear;

    @Column(name = "CMF_TYPE", nullable = false, length = 30)
    private String cmf_type;

    @Column(name = "CMF_HALLAMOUNT", nullable = false, length = 3)
    private int cmf_hallamount;

    public Cmp_features() {
    }

    public Cmp_features(String cmf_name, Country cnt_id, String cmf_address, int cmf_constructionyear, String cmf_type, int cmf_hallamount) {
        this.cmf_name = cmf_name;
        this.cnt_id = cnt_id;
        this.cmf_address = cmf_address;
        this.cmf_constructionyear = cmf_constructionyear;
        this.cmf_type = cmf_type;
        this.cmf_hallamount = cmf_hallamount;
    }

    public int getCmf_id() {
        return cmf_id;
    }

    public void setCmf_id(int cmf_id) {
        this.cmf_id = cmf_id;
    }

    public String getCmf_name() {
        return cmf_name;
    }

    public void setCmf_name(String cmf_name) {
        this.cmf_name = cmf_name;
    }

    public Country getCnt_id() {
        return cnt_id;
    }

    public void setCnt_id(Country cnt_id) {
        this.cnt_id = cnt_id;
    }

    public String getCmf_address() {
        return cmf_address;
    }

    public void setCmf_address(String cmf_address) {
        this.cmf_address = cmf_address;
    }

    public int getCmf_constructionyear() {
        return cmf_constructionyear;
    }

    public void setCmf_constructionyear(int cmf_constructionyear) {
        this.cmf_constructionyear = cmf_constructionyear;
    }

    public String getCmf_type() {
        return cmf_type;
    }

    public void setCmf_type(String cmf_type) {
        this.cmf_type = cmf_type;
    }

    public int getCmf_hallamount() {
        return cmf_hallamount;
    }

    public void setCmf_hallamount(int cmf_hallamount) {
        this.cmf_hallamount = cmf_hallamount;
    }
}
