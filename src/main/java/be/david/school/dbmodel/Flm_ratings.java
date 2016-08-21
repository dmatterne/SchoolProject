package be.david.school.dbmodel;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 *
 */
@Entity
@Table(name = "FLM_RATINGS", uniqueConstraints =
@UniqueConstraint(name = "pr_fra_id" , columnNames = {"FRA_ID"}))
public class Flm_ratings {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FRA")
    @SequenceGenerator(name = "SEQ_FRA", sequenceName = "SEQ_FRA", initialValue = 1,allocationSize = 1)
    @Column(name="FRA_ID" )
    private int fra_id;

    @Column(name="FRA_NAME", nullable = false, length = 20, unique = true)
    private String fra_name;

    public Flm_ratings() {
    }

    public Flm_ratings(String fra_name) {
        this.fra_name = fra_name;
    }

    public int getFra_id() {
        return fra_id;
    }

    public void setFra_id(int fra_id) {
        this.fra_id = fra_id;
    }

    public String getFra_name() {
        return fra_name;
    }

    public void setFra_name(String fra_name) {
        this.fra_name = fra_name;
    }
}