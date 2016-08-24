package be.david.school.dbmodel;

import be.david.school.Enums.Gender;
import be.david.school.interfaces.DropDownMarker;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "PERSONS", uniqueConstraints = @UniqueConstraint(name = "pk_persons", columnNames = {"PRS_ID"}))
public class Persons implements DropDownMarker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRS")
    @SequenceGenerator(name = "SEQ_PRS", sequenceName = "SEQ_PRS", initialValue = 1, allocationSize = 1)
    @Column(name = "PRS_ID", length = 10)
    private int prs_id;
    @Column(name = "PRS_NAME", length = 30)
    private String prs_name;
    @Column(name = "PRS_BIRTHDATE")
    private LocalDate prs_birthdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRS_SEX", length = 1)
    private Gender prs_sex;
    @OneToOne
    @JoinColumn(name = "CNT_ID", foreignKey = @ForeignKey(name="rf_cnt_id_prs"))
    private Country cnt_id;

    public Persons() {
    }

    public Persons(String prs_name, LocalDate prs_birthdate, Gender prs_sex, Country cnt_id) {
        this.prs_name = prs_name;
        this.prs_birthdate = prs_birthdate;
        this.prs_sex = prs_sex;
        this.cnt_id = cnt_id;
    }

    public int getPrs_id() {
        return prs_id;
    }

    public void setPrs_id(int prs_id) {
        this.prs_id = prs_id;
    }

    public String getPrs_name() {
        return prs_name;
    }

    public void setPrs_name(String prs_name) {
        this.prs_name = prs_name;
    }

    public LocalDate getPrs_birthdate() {
        return prs_birthdate;
    }

    public void setPrs_birthdate(LocalDate prs_birthdate) {
        this.prs_birthdate = prs_birthdate;
    }

    public Gender getPrs_sex() {
        return prs_sex;
    }

    public void setPrs_sex(Gender prs_sex) {
        this.prs_sex = prs_sex;
    }

    public Country getCnt_id() {
        return cnt_id;
    }

    public void setCnt_id(Country cnt_id) {
        this.cnt_id = cnt_id;
    }
}
