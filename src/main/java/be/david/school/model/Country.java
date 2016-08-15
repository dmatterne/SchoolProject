package be.david.school.model;

import javax.persistence.*;

/**
 * Created by David on 7/08/2016.
 */
@Entity
@Table(name="COUNTRIES", uniqueConstraints = @UniqueConstraint(name = "pr_countries" , columnNames = {"CNT_ID"}))
public class Country {

    @Id
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SEQ_CNT")
    @SequenceGenerator(name = "SEQ_CNT", sequenceName = "SEQ_CNT")
    private int cnt_id;


    @Column(name = "CNT_NAME", nullable = false)
    private String cnt_name;

    @Column(name = "CNT_CURRENCY", nullable = false)
    private String cnt_currency;

    @Column(name = "CNT_TAX", nullable = false)
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


//    CREATE TABLE COUNTRIES
//        (cnt_id				NUMBER(3) 	CONSTRAINT pr_countries 	PRIMARY KEY
//,cnt_name	 		VARCHAR2(15)	CONSTRAINT nn_cnt_name		NOT NULL
//        CHECK(cnt_name = initcap(cnt_name))
//        ,cnt_currency	 		VARCHAR2(3)	CONSTRAINT nn_cnt_currency	NOT NULL
//        ,cnt_tax	 		NUMBER(3)	CONSTRAINT nn_cnt_tax		NOT NULL
//        )
//        /