package be.david.school.dbmodel;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "ORG_FILMS", uniqueConstraints = @UniqueConstraint(name = "pk_org_films", columnNames = {"ORF_ID"}))
public class Org_films {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_ORF")
    @SequenceGenerator(name = "SEQ_ORF", sequenceName = "SEQ_ORF", initialValue = 1,allocationSize = 1)
    @Column(name = "ORF_ID", length = 8)
    private int orf_id;

    @Column(name = "ORF_PLAYING", nullable = false, length = 30)
    private String orf_playing;

    @OneToOne
    @JoinColumn(name = "FLI_ID", referencedColumnName = "FLI_ID", foreignKey = @ForeignKey(name = "rf_fli_id_orf"))
    private Flm_info fli_id;

    public Org_films() {
    }

    public Org_films(String orf_playing, Flm_info fli_id) {
        this.orf_playing = orf_playing;
        this.fli_id = fli_id;
    }

    public int getOrf_id() {
        return orf_id;
    }

    public void setOrf_id(int orf_id) {
        this.orf_id = orf_id;
    }

    public String getOrf_playing() {
        return orf_playing;
    }

    public void setOrf_playing(String orf_playing) {
        this.orf_playing = orf_playing;
    }

    public Flm_info getFli_id() {
        return fli_id;
    }

    public void setFli_id(Flm_info fli_id) {
        this.fli_id = fli_id;
    }
}
