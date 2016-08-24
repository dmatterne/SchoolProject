package be.david.school.dbmodel;

import be.david.school.interfaces.DropDownMarker;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name="FLM_INFO", uniqueConstraints = @UniqueConstraint(name="pr_flm_info", columnNames = {"FLI_ID"}))
public class Flm_info implements DropDownMarker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FLI")
    @SequenceGenerator(name = "SEQ_FLI", sequenceName = "SEQ_FLI", initialValue = 1, allocationSize = 1)
    @Column(name = "FLI_ID", length = 15)
    private int fli_id;

    @Column(name = "FLI_NAME", nullable = false, length = 30)
    private String fli_name;
    @Column(name = "FLI_LENGTH", nullable = false, length = 3)
    private int fli_length;
    @Column(name = "FLI_WEBSITE", length = 50)
    private String fli_website;
    @Column(name = "FLI_DESCRIPTION", length = 4000)
    private String fli_description;
    @Column(name = "FLI_COLOR", nullable = false, length = 10)
    private String fli_color;

    @Column(name = "FLI_RELEASEDATE", nullable = false)
    private LocalDate fli_releasedate;

    @Column(name = "FLI_SOUNDTRACK", length = 50)
    private String fli_soundtrack;

    @Column(name = "FLI_FORMAT", nullable = false, length = 10)
    private String fli_format;

    @Column(name = "FLI_PRICE", nullable = false, length = 5)
    private double fli_price;


    @Column(name = "FLI_TARGETGROUP", length = 40 )
    private String fli_targetgroup;

    @OneToOne
    @JoinColumn(name = "GNR_ID", referencedColumnName = "GNR_ID", foreignKey = @ForeignKey(name="rf_gnr_id_fli"))
    private Genres gnr_id;

    @Column(name = "FLI_PRODUCTIONHOUSE", nullable = false, length = 30 )
    private String fli_productionHouse;

    @OneToOne
    @JoinColumn(name = "CNT_ID", referencedColumnName = "CNT_ID", foreignKey = @ForeignKey(name="rf_cnt_id_fli"))
    private Country cnt_id;

    @OneToOne
    @JoinColumn(name = "DST_ID", referencedColumnName = "DST_ID", foreignKey = @ForeignKey(name="rf_dst_id_fli"))
    private Distributors dst_id;


    public Flm_info() {
    }

    public Flm_info(String fli_name, int fli_length, String fli_website, String fli_description, String fli_color, LocalDate fli_releasedate, String fli_soundtrack, String fli_format, double fli_price, String fli_targetgroup, Genres gnr_id, String fli_productionHouse, Country cnt_id, Distributors dst_id) {
        this.fli_name = fli_name;
        this.fli_length = fli_length;
        this.fli_website = fli_website;
        this.fli_description = fli_description;
        this.fli_color = fli_color;
        this.fli_releasedate = fli_releasedate;
        this.fli_soundtrack = fli_soundtrack;
        this.fli_format = fli_format;
        this.fli_price = fli_price;
        this.fli_targetgroup = fli_targetgroup;
        this.gnr_id = gnr_id;
        this.fli_productionHouse = fli_productionHouse;
        this.cnt_id = cnt_id;
        this.dst_id = dst_id;
    }

    public int getFli_id() {
        return fli_id;
    }

    public void setFli_id(int fli_id) {
        this.fli_id = fli_id;
    }

    public String getFli_name() {
        return fli_name;
    }

    public void setFli_name(String fli_name) {
        this.fli_name = fli_name;
    }

    public int getFli_length() {
        return fli_length;
    }

    public void setFli_length(int fli_length) {
        this.fli_length = fli_length;
    }

    public String getFli_website() {
        return fli_website;
    }

    public void setFli_website(String fli_website) {
        this.fli_website = fli_website;
    }

    public String getFli_description() {
        return fli_description;
    }

    public void setFli_description(String fli_description) {
        this.fli_description = fli_description;
    }

    public String getFli_color() {
        return fli_color;
    }

    public void setFli_color(String fli_color) {
        this.fli_color = fli_color;
    }

    public LocalDate getFli_releasedate() {
        return fli_releasedate;
    }

    public void setFli_releasedate(LocalDate fli_releasedate) {
        this.fli_releasedate = fli_releasedate;
    }

    public String getFli_soundtrack() {
        return fli_soundtrack;
    }

    public void setFli_soundtrack(String fli_soundtrack) {
        this.fli_soundtrack = fli_soundtrack;
    }

    public String getFli_format() {
        return fli_format;
    }

    public void setFli_format(String fli_format) {
        this.fli_format = fli_format;
    }

    public double getFli_price() {
        return fli_price;
    }

    public void setFli_price(double fli_price) {
        this.fli_price = fli_price;
    }

    public String getFli_targetgroup() {
        return fli_targetgroup;
    }

    public void setFli_targetgroup(String fli_targetgroup) {
        this.fli_targetgroup = fli_targetgroup;
    }

    public Genres getGnr_id() {
        return gnr_id;
    }

    public void setGnr_id(Genres gnr_id) {
        this.gnr_id = gnr_id;
    }

    public String getFli_productionHouse() {
        return fli_productionHouse;
    }

    public void setFli_productionHouse(String fli_productionHouse) {
        this.fli_productionHouse = fli_productionHouse;
    }

    public Country getCnt_id() {
        return cnt_id;
    }

    public void setCnt_id(Country cnt_id) {
        this.cnt_id = cnt_id;
    }

    public Distributors getDst_id() {
        return dst_id;
    }

    public void setDst_id(Distributors dst_id) {
        this.dst_id = dst_id;
    }
}