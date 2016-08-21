package be.david.school.dbmodel;

import be.david.school.Enums.Gender;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "LOGINS", uniqueConstraints = @UniqueConstraint(name = "pk_logins", columnNames = {"LGN_ID"}))
public class Logins {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LGN")
    @SequenceGenerator(name = "SEQ_LGN", sequenceName = "SEQ_LGN", initialValue = 1, allocationSize = 1)
    @Column(name = "LGN_ID", length = 38)
    private int lgn_id;

    @Column(name = "LGN_USERNAME", length = 38)
    private String lgn_username;
    @Column(name = "LGN_PASSWORD", length = 38)
    private String lgn_password;
    @OneToOne
    @JoinColumn(name = "USR_ID", foreignKey = @ForeignKey(name="rf_usr_id_lgn"))
    private Users usr_id;
    @Column(name = "LGN_FUNCTION", length = 100)
    private String lgn_function;

    @OneToOne
    @JoinColumn(name = "CNT_ID", foreignKey = @ForeignKey(name="rf_cnt_id_lgn"))
    private Country cnt_id;
    @Column(name = "LGN_ADDRESS", length = 40,  nullable = false)
    private String lgn_address;
    @Column(name = "LGN_BIRTHDATE")
    private LocalDate lgn_birthdate;
    @Column(name = "LGN_EMAIL", length = 30,  nullable = false)
    private String lgn_email;

    @Column(name = "LGN_NAME", length = 15,  nullable = false)
    private String lgn_name;

    @Column(name = "LGN_SEX", length = 1,  nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender lgn_sex;
    @Column(name = "LGN_TEL", length = 15,  nullable = false)
    private int lgn_tel;


    public Logins() {
    }

    public Logins(String lgn_username, String lgn_password, Users usr_id, String lgn_function, Country cnt_id, String lgn_address, LocalDate lgn_birthdate, String lgn_email, String lgn_name, Gender lgn_sex, int lgn_tel) {
        this.lgn_username = lgn_username;
        this.lgn_password = lgn_password;
        this.usr_id = usr_id;
        this.lgn_function = lgn_function;
        this.cnt_id = cnt_id;
        this.lgn_address = lgn_address;
        this.lgn_birthdate = lgn_birthdate;
        this.lgn_email = lgn_email;
        this.lgn_name = lgn_name;
        this.lgn_sex = lgn_sex;
        this.lgn_tel = lgn_tel;
    }

    public int getLgn_id() {
        return lgn_id;
    }

    public void setLgn_id(int lgn_id) {
        this.lgn_id = lgn_id;
    }

    public String getLgn_username() {
        return lgn_username;
    }

    public void setLgn_username(String lgn_username) {
        this.lgn_username = lgn_username;
    }

    public String getLgn_password() {
        return lgn_password;
    }

    public void setLgn_password(String lgn_password) {
        this.lgn_password = lgn_password;
    }

    public Users getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(Users usr_id) {
        this.usr_id = usr_id;
    }

    public String getLgn_function() {
        return lgn_function;
    }

    public void setLgn_function(String lgn_function) {
        this.lgn_function = lgn_function;
    }

    public Country getCnt_id() {
        return cnt_id;
    }

    public void setCnt_id(Country cnt_id) {
        this.cnt_id = cnt_id;
    }

    public String getLgn_address() {
        return lgn_address;
    }

    public void setLgn_address(String lgn_address) {
        this.lgn_address = lgn_address;
    }

    public LocalDate getLgn_birthdate() {
        return lgn_birthdate;
    }

    public void setLgn_birthdate(LocalDate lgn_birthdate) {
        this.lgn_birthdate = lgn_birthdate;
    }

    public String getLgn_email() {
        return lgn_email;
    }

    public void setLgn_email(String lgn_email) {
        this.lgn_email = lgn_email;
    }

    public String getLgn_name() {
        return lgn_name;
    }

    public void setLgn_name(String lgn_name) {
        this.lgn_name = lgn_name;
    }

    public Gender getLgn_sex() {
        return lgn_sex;
    }

    public void setLgn_sex(Gender lgn_sex) {
        this.lgn_sex = lgn_sex;
    }

    public int getLgn_tel() {
        return lgn_tel;
    }

    public void setLgn_tel(int lgn_tel) {
        this.lgn_tel = lgn_tel;
    }
}