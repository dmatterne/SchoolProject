package be.david.school.dbmodel;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "CUSTOMERS" , uniqueConstraints = @UniqueConstraint(name = "pr_customer", columnNames = {"CST_ID"}))
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CST")
    @SequenceGenerator(sequenceName = "SEQ_CST",name = "SEQ_CST",initialValue = 1, allocationSize = 1)
    @Column(name = "CST_ID", length = 10)
    private int cst_id;

    @Column(name = "CST_BIRTHDATE", nullable = false)
    private LocalDate cst_birthdate;

    @Column(name = "CST_NAME", length = 40, nullable = false)
    private String cst_name;

    @Column(name = "CST_ADDRESS", length = 40)
    private String cst_address;

    @Column(name = "CST_TEL", length = 15)
    private int cst_tel;

    @Column(name = "CST_EMAIL", length = 30)
    private String email;

    public Customers() {
    }

    public int getCst_id() {
        return cst_id;
    }

    public void setCst_id(int cst_id) {
        this.cst_id = cst_id;
    }

    public LocalDate getCst_birthdate() {
        return cst_birthdate;
    }

    public void setCst_birthdate(LocalDate cst_birthdate) {
        this.cst_birthdate = cst_birthdate;
    }

    public String getCst_name() {
        return cst_name;
    }

    public void setCst_name(String cst_name) {
        this.cst_name = cst_name;
    }

    public String getCst_address() {
        return cst_address;
    }

    public void setCst_address(String cst_address) {
        this.cst_address = cst_address;
    }

    public int getCst_tel() {
        return cst_tel;
    }

    public void setCst_tel(int cst_tel) {
        this.cst_tel = cst_tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}