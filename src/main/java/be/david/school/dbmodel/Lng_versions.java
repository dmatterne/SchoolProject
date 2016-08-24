package be.david.school.dbmodel;

import be.david.school.interfaces.DropDownMarker;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "LNG_VERSIONS", uniqueConstraints = @UniqueConstraint(name = "pr_lng_versions", columnNames = {"LNV_ID"}))
public class Lng_versions implements DropDownMarker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LNV")
    @SequenceGenerator(name = "SEQ_LNV", sequenceName = "SEQ_LNV", initialValue = 1, allocationSize = 1)
    @Column(name = "LNV_ID", length = 15)
    private int lnv_id;

    @Column(name = "LNV_NAME", nullable = false, length = 30)
    private String lnv_name;

    @OneToOne
    @JoinColumn(name = "CNT_ID", foreignKey = @ForeignKey(name="rf_cnt_id_lnv"))
    private Country cnt_id;

    @OneToOne
    @JoinColumn(name = "NTF_ID", foreignKey = @ForeignKey(name="rf_ntf_id_lnv"))
    private Ntn_films Ntf_id;

    @OneToOne
    @JoinColumn(name = "SND_ID", foreignKey = @ForeignKey(name="rf_snd_id_lnv"))
    private Sounds snd_id;

    @OneToOne
    @JoinColumn(name = "SBT_ID", foreignKey = @ForeignKey(name="rf_sbt_id_lnv"))
    private Subtitles Sbt_id;

    @OneToOne
    @JoinColumn(name = "FRM_ID", foreignKey = @ForeignKey(name="rf_frm_id_lnv"))
    private Formats frm_id;

    @OneToOne
    @JoinColumn(name = "LNG_ID", foreignKey = @ForeignKey(name="rf_lng_id_lnv"))
    private Languages lng_id;

    @Column(name = "LNV_RELEASEDATE", nullable = false)
    private LocalDate lnv_releasedate;

    @Column(name = "LNV_LENGTH", nullable = false, length = 4)
    private int lnv_length;

    public Lng_versions() {
    }

    public Lng_versions(String lnv_name, Country cnt_id, Ntn_films ntf_id, Sounds snd_id, Subtitles sbt_id, Formats frm_id, Languages lng_id, LocalDate lnv_releasedate, int lnv_length) {
        this.lnv_name = lnv_name;
        this.cnt_id = cnt_id;
        Ntf_id = ntf_id;
        this.snd_id = snd_id;
        Sbt_id = sbt_id;
        this.frm_id = frm_id;
        this.lng_id = lng_id;
        this.lnv_releasedate = lnv_releasedate;
        this.lnv_length = lnv_length;
    }

    public int getLnv_id() {
        return lnv_id;
    }

    public void setLnv_id(int lnv_id) {
        this.lnv_id = lnv_id;
    }

    public String getLnv_name() {
        return lnv_name;
    }

    public void setLnv_name(String lnv_name) {
        this.lnv_name = lnv_name;
    }

    public Country getCnt_id() {
        return cnt_id;
    }

    public void setCnt_id(Country cnt_id) {
        this.cnt_id = cnt_id;
    }

    public Ntn_films getNtf_id() {
        return Ntf_id;
    }

    public void setNtn_id(Ntn_films ntf_id) {
        Ntf_id = ntf_id;
    }

    public Sounds getSnd_id() {
        return snd_id;
    }

    public void setSnd_id(Sounds snd_id) {
        this.snd_id = snd_id;
    }

    public Subtitles getSbt_id() {
        return Sbt_id;
    }

    public void setSbt_id(Subtitles sbt_id) {
        Sbt_id = sbt_id;
    }

    public Formats getFrm_id() {
        return frm_id;
    }

    public void setFrm_id(Formats frm_id) {
        this.frm_id = frm_id;
    }

    public Languages getLng_id() {
        return lng_id;
    }

    public void setLng_id(Languages lng_id) {
        this.lng_id = lng_id;
    }

    public LocalDate getLnv_releasedate() {
        return lnv_releasedate;
    }

    public void setLnv_releasedate(LocalDate lnv_releasedate) {
        this.lnv_releasedate = lnv_releasedate;
    }

    public int getLnv_length() {
        return lnv_length;
    }

    public void setLnv_length(int lnv_length) {
        this.lnv_length = lnv_length;
    }
}
