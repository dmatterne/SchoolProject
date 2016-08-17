package be.david.school.model;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "HLL_COMPLEX", uniqueConstraints = @UniqueConstraint(name="pr_hll_complex", columnNames = {"HLC_ID","CMF_ID"}))
public class Hll_complex {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_HLC")
    @SequenceGenerator(name="SEQ_HLC", sequenceName = "SEQ_HLC", initialValue = 1, allocationSize = 1)
    @Column(name = "HLC_ID", length = 3)
    private int hlc_id;

    @OneToOne
    @JoinColumn(name = "CMF_ID", foreignKey = @ForeignKey(name="rf_cmf_id_hlc"))
    private Cmp_features cmf_id;
    @Enumerated(EnumType.STRING)
    @Column(name = "HLC_TWINSEAT", length = 3, nullable = false)
    private YesNo hlc_twinseat;
    @Enumerated(EnumType.STRING)
    @Column(name = "HLC_WHEELCHAIR", length = 3, nullable = false)
    private YesNo hlc_wheelchair;
    @Enumerated(EnumType.STRING)
    @Column(name = "HLC_DIGITALVERSION", length = 3, nullable = false)
    private YesNo hlc_digitalversion;
    @Column(name = "HLC_CUPHOLDER", length = 3, nullable = false)
    @Enumerated(EnumType.STRING)
    private YesNo hlc_cupholder;
    @Column(name = "HLC_CAPACITY", length = 4, nullable = false)
    private int hlc_capacity;
    @Enumerated(EnumType.STRING)
    @Column(name = "HLC_BOOKABLE", length = 3, nullable = false)
    private YesNo hlc_bookable;
    @Column(name = "HLC_DISTANCESCREENPROJECTION", length = 4, nullable = false)
    private int hlc_distancescreenprojection;
    @Column(name = "HLC_SPACEINFRONTOFSCREEN", length = 10, nullable = false)
    private String hlc_spaceinfrontofscreen;
    @Column(name = "HLC_SCREENWIDTH", length = 10, nullable = false)
    private String hlc_screenwidth;

    public Hll_complex() {
    }

    public Hll_complex(int hlc_id, int cmf_id, YesNo hlc_twinseat, YesNo hlc_wheelchair, YesNo hlc_digitalversion, YesNo hlc_cupholder, int hlc_capacity, YesNo hlc_bookable, int hlc_distancescreenprojection, String hlc_spaceinfrontofscreen, String hlc_screenwidth) {
        this.hlc_id = hlc_id;
        this.cmf_id = cmf_id;
        this.hlc_twinseat = hlc_twinseat;
        this.hlc_wheelchair = hlc_wheelchair;
        this.hlc_digitalversion = hlc_digitalversion;
        this.hlc_cupholder = hlc_cupholder;
        this.hlc_capacity = hlc_capacity;
        this.hlc_bookable = hlc_bookable;
        this.hlc_distancescreenprojection = hlc_distancescreenprojection;
        this.hlc_spaceinfrontofscreen = hlc_spaceinfrontofscreen;
        this.hlc_screenwidth = hlc_screenwidth;
    }

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

    public YesNo getHlc_twinseat() {
        return hlc_twinseat;
    }

    public void setHlc_twinseat(YesNo hlc_twinseat) {
        this.hlc_twinseat = hlc_twinseat;
    }

    public YesNo getHlc_wheelchair() {
        return hlc_wheelchair;
    }

    public void setHlc_wheelchair(YesNo hlc_wheelchair) {
        this.hlc_wheelchair = hlc_wheelchair;
    }

    public YesNo getHlc_digitalversion() {
        return hlc_digitalversion;
    }

    public void setHlc_digitalversion(YesNo hlc_digitalversion) {
        this.hlc_digitalversion = hlc_digitalversion;
    }

    public YesNo getHlc_cupholder() {
        return hlc_cupholder;
    }

    public void setHlc_cupholder(YesNo hlc_cupholder) {
        this.hlc_cupholder = hlc_cupholder;
    }

    public int getHlc_capacity() {
        return hlc_capacity;
    }

    public void setHlc_capacity(int hlc_capacity) {
        this.hlc_capacity = hlc_capacity;
    }

    public YesNo getHlc_bookable() {
        return hlc_bookable;
    }

    public void setHlc_bookable(YesNo hlc_bookable) {
        this.hlc_bookable = hlc_bookable;
    }

    public int getHlc_distancescreenprojection() {
        return hlc_distancescreenprojection;
    }

    public void setHlc_distancescreenprojection(int hlc_distancescreenprojection) {
        this.hlc_distancescreenprojection = hlc_distancescreenprojection;
    }

    public String getHlc_spaceinfrontofscreen() {
        return hlc_spaceinfrontofscreen;
    }

    public void setHlc_spaceinfrontofscreen(String hlc_spaceinfrontofscreen) {
        this.hlc_spaceinfrontofscreen = hlc_spaceinfrontofscreen;
    }

    public String getHlc_screenwidth() {
        return hlc_screenwidth;
    }

    public void setHlc_screenwidth(String hlc_screenwidth) {
        this.hlc_screenwidth = hlc_screenwidth;
    }
}