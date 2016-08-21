package be.david.school.dbmodel;

import be.david.school.Enums.FilmStatus;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "NTN_FILMS", uniqueConstraints = @UniqueConstraint(name = "pr_ntn_films", columnNames = {"NTF_ID"}))
public class Ntn_films {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NTF")
    @SequenceGenerator(name = "SEQ_NTF", sequenceName = "SEQ_NTF", initialValue = 1, allocationSize = 1)
    @Column(name = "NTF_ID", length = 15)
    private int ntf_id;

    @Column(name = "NTF_NAME", length = 30, nullable = false)
    private String ntf_name;

    @OneToOne
    @JoinColumn(name = "ORF_ID", foreignKey = @ForeignKey(name="rf_orf_id"))
    private Org_films orf_id;

    @OneToOne
    @JoinColumn(name = "FRA_ID", foreignKey = @ForeignKey(name="rf_fra_id"))
    private Flm_ratings fra_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "NTF_STATUS", length = 11, nullable = false)
    private FilmStatus ntf_status;

    @Column(name = "NTF_AGES", length = 2)
    private int ntf_ages;

    public Ntn_films() {
    }

    public Ntn_films(String ntf_name, Org_films orf_id, Flm_ratings fra_id, FilmStatus ntf_status, int ntf_ages) {
        this.ntf_name = ntf_name;
        this.orf_id = orf_id;
        this.fra_id = fra_id;
        this.ntf_status = ntf_status;
        this.ntf_ages = ntf_ages;
    }

    public int getNtf_id() {
        return ntf_id;
    }

    public void setNtf_id(int ntf_id) {
        this.ntf_id = ntf_id;
    }

    public String getNtf_name() {
        return ntf_name;
    }

    public void setNtf_name(String ntf_name) {
        this.ntf_name = ntf_name;
    }

    public Org_films getOrf_id() {
        return orf_id;
    }

    public void setOrf_id(Org_films orf_id) {
        this.orf_id = orf_id;
    }

    public Flm_ratings getFra_id() {
        return fra_id;
    }

    public void setFra_id(Flm_ratings fra_id) {
        this.fra_id = fra_id;
    }

    public FilmStatus getNtf_status() {
        return ntf_status;
    }

    public void setNtf_status(FilmStatus ntf_status) {
        this.ntf_status = ntf_status;
    }

    public int getNtf_ages() {
        return ntf_ages;
    }

    public void setNtf_ages(int ntf_ages) {
        this.ntf_ages = ntf_ages;
    }
}