package be.david.school.dbmodel;

import be.david.school.interfaces.DropDownMarker;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "DISCOUNTS", uniqueConstraints = @UniqueConstraint(name = "pr_discounts",columnNames = {"DSC_ID"}))
public class Discounts implements DropDownMarker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DSC")
    @SequenceGenerator(name = "SEQ_DSC",sequenceName = "SEQ_DSC",initialValue = 1,allocationSize = 1)
    @Column(name = "DSC_ID", length = 3)
    private int dsc_id;

    @Column(name = "DSC_NAME", nullable = false, length = 30)
    private String dsc_name;

    @Column(name = "DSC_AMOUNT", nullable = false, precision = 5, scale = 2)
    private double dsc_amount;

    public Discounts() {

    }

    public Discounts(String dsc_name, double dsc_amount) {
        this.dsc_name = dsc_name;
        this.dsc_amount = dsc_amount;
    }

    public int getDsc_id() {
        return dsc_id;
    }

    public void setDsc_id(int dsc_id) {
        this.dsc_id = dsc_id;
    }

    public String getDsc_name() {
        return dsc_name;
    }

    public void setDsc_name(String dsc_name) {
        this.dsc_name = dsc_name;
    }

    public double getDsc_amount() {
        return dsc_amount;
    }

    public void setDsc_amount(double dsc_amount) {
        this.dsc_amount = dsc_amount;
    }
}