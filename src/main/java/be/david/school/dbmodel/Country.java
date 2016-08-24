package be.david.school.dbmodel;

import be.david.school.interfaces.DropDownMarker;

import javax.persistence.*;

/**
 * Created by David on 7/08/2016.
 */
@Entity
@Table(name="COUNTRIES", uniqueConstraints = @UniqueConstraint(name = "pr_countries" , columnNames = {"CNT_ID"}))
public class Country implements DropDownMarker {

    @Id
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SEQ_CNT")
    @SequenceGenerator(name = "SEQ_CNT", sequenceName = "SEQ_CNT")
    @Column (name = "CNT_ID", length = 3)
    private int cnt_id;


    @Column(name = "CNT_NAME", nullable = false, length = 15)
    private String cnt_name;

    @Column(name = "CNT_CURRENCY", nullable = false, length = 3)
    private String cnt_currency;

    @Column(name = "CNT_TAX", nullable = false, length = 3)
    private int cnt_tax;


    protected Country() {
    }

    public Country(String cnt_name, String cnt_currency, int cnt_tax) {
        this.cnt_name = cnt_name;
        this.cnt_currency = cnt_currency;
        this.cnt_tax = cnt_tax;
    }

    public int getCnt_id() {
        return cnt_id;
    }

    public void setCnt_id(int cnt_id) {
        this.cnt_id = cnt_id;
    }

    public String getCnt_name() {
        return cnt_name;
    }

    public void setCnt_name(String cnt_name) {
        this.cnt_name = cnt_name;
    }

    public String getCnt_currency() {
        return cnt_currency;
    }

    public void setCnt_currency(String cnt_currency) {
        this.cnt_currency = cnt_currency;
    }

    public int getCnt_tax() {
        return cnt_tax;
    }

    public void setCnt_tax(int cnt_tax) {
        this.cnt_tax = cnt_tax;
    }
}