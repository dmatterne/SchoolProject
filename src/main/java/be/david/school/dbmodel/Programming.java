package be.david.school.dbmodel;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "PROGRAMMING", uniqueConstraints = @UniqueConstraint(name = "pk_programming", columnNames = {"PRG_ID"}))
public class Programming {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRG")
    @SequenceGenerator(name = "SEQ_PRG", sequenceName = "SEQ_PRG", initialValue = 1, allocationSize = 1)
    @Column(name = "PRG_ID", length = 30)
    private int prg_id;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "cmf_id", referencedColumnName = "cmf_id", updatable=false, insertable=false),
            @JoinColumn(name = "hlc_id", referencedColumnName = "hlc_id", updatable=false, insertable=false)
    }
    )
    private Hll_complex hlc;

    @OneToOne
    @JoinColumn(name = "FLI_ID", foreignKey = @ForeignKey(name="rf_fli_id_prg"))
    private Flm_info fli_id;

    @Column(name = "PRG_DATETIME", nullable = false)
    private LocalDateTime prg_datetime;

    @Column(name = "PRG_PRICE", nullable = false, precision = 6, scale = 2)
    private double prg_price;

    public Programming() {

    }

    public Programming(Hll_complex hlc, Flm_info fli_id, LocalDateTime prg_datetime, double prg_price) {
        this.hlc = hlc;
        this.fli_id = fli_id;
        this.prg_datetime = prg_datetime;
        this.prg_price = prg_price;
    }

    public int getPrg_id() {
        return prg_id;
    }

    public void setPrg_id(int prg_id) {
        this.prg_id = prg_id;
    }

    public Hll_complex getHlc() {
        return hlc;
    }

    public void setHlc(Hll_complex hlc) {
        this.hlc = hlc;
    }

    public Flm_info getFli_id() {
        return fli_id;
    }

    public void setFli_id(Flm_info fli_id) {
        this.fli_id = fli_id;
    }

    public LocalDateTime getPrg_datetime() {
        return prg_datetime;
    }

    public void setPrg_datetime(LocalDateTime prg_datetime) {
        this.prg_datetime = prg_datetime;
    }

    public double getPrg_price() {
        return prg_price;
    }

    public void setPrg_price(double prg_price) {
        this.prg_price = prg_price;
    }
}


