package be.david.school.dbmodel;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "TICKETS", uniqueConstraints = @UniqueConstraint(name = "pk_tickets", columnNames = {"TCK_ID"}))
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TCK")
    @SequenceGenerator(sequenceName = "SEQ_TCK",name = "SEQ_TCK",initialValue = 1, allocationSize = 1)
    @Column(name = "TCK_ID", length = 38)
    private int tck_id;

    @OneToOne
    @JoinColumn(name = "PRG_ID", referencedColumnName = "PRG_ID",foreignKey = @ForeignKey(name="rf_prg_id_tck"))
    private Programming prg_id;

    @OneToOne
    @JoinColumn(name = "CST_ID", referencedColumnName = "CST_ID",foreignKey = @ForeignKey(name="rf_cst_id_tck"))
    private Customers cst_id;

    @OneToOne
    @JoinColumn(name = "DSC_ID", referencedColumnName = "DSC_ID",foreignKey = @ForeignKey(name="rf_dsc_id_tck"))
    private Discounts dsc_id;

    @Column(name = "TCK_PRICE", nullable = false, precision = 5, scale = 2)
    private double tck_price;

    @OneToOne
    @JoinColumn(name = "USR_ID", referencedColumnName = "USR_ID",foreignKey = @ForeignKey(name="rf_usr_id_tck"))
    private Users usr_id;

    public Tickets() {
    }

    public Tickets(Programming prg_id, Customers cst_id, Discounts dsc_id, double tck_price, Users usr_id) {
        this.prg_id = prg_id;
        this.cst_id = cst_id;
        this.dsc_id = dsc_id;
        this.tck_price = tck_price;
        this.usr_id = usr_id;
    }

    public int getTck_id() {
        return tck_id;
    }

    public void setTck_id(int tck_id) {
        this.tck_id = tck_id;
    }

    public Programming getPrg_id() {
        return prg_id;
    }

    public void setPrg_id(Programming prg_id) {
        this.prg_id = prg_id;
    }

    public Customers getCst_id() {
        return cst_id;
    }

    public void setCst_id(Customers cst_id) {
        this.cst_id = cst_id;
    }

    public Discounts getDsc_id() {
        return dsc_id;
    }

    public void setDsc_id(Discounts dsc_id) {
        this.dsc_id = dsc_id;
    }

    public double getTck_price() {
        return tck_price;
    }

    public void setTck_price(double tck_price) {
        this.tck_price = tck_price;
    }

    public Users getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(Users usr_id) {
        this.usr_id = usr_id;
    }
}

//    CREATE TABLE TICKETS
//        (tck_id				NUMBER(38)	CONSTRAINT pr_tickets 		PRIMARY KEY
//        ,prg_id				NUMBER(30)	CONSTRAINT rf_tickets_prg_id	REFERENCES PROGRAMMING
//                ,cst_id				NUMBER(30)	CONSTRAINT rf_tickets_cst_id	REFERENCES CUSTOMERS
//                ,dsc_id				NUMBER(3)	CONSTRAINT rf_tickets_dsc_id	REFERENCES DISCOUNTS
//                ,tck_price			NUMBER(3,2)	CONSTRAINT nn_tck_price		NOT NULL
//                ,usr_id			        NUMBER(15)	CONSTRAINT rf_tck_users		REFERENCES USERS(usr_id)
//                )
//                /


