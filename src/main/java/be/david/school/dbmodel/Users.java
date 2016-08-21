package be.david.school.dbmodel;

import be.david.school.Enums.Gender;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by David on 17/08/2016.
 */
@Entity
@Table(name = "USERS", uniqueConstraints = @UniqueConstraint(name="pk_users", columnNames = {"USR_ID"}))
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USR")
    @SequenceGenerator(sequenceName = "SEQ_USR",name = "SEQ_USR",initialValue = 1, allocationSize = 1)
    @Column(name = "USR_ID", length = 38)
    private int usr_id;

    @Column(name = "USR_NAME", nullable = false, length = 50)
    private String usr_name;
    @Column(name = "USR_ADDRESS", nullable = false, length = 40)
    private String usr_address;
    @Column(name = "USR_BIRTHDAY", nullable = false)
    private LocalDate usr_birthday;
    @Column(name = "USR_EMAIL", nullable = false, length = 30)
    private String usr_email;
    @Column(name = "USR_FUNCTION", nullable = false, length = 30)
    private String usr_function;
    @Column(name = "USR_PASSWORD", nullable = false, length = 100)
    private String usr_password;
    @Column(name = "USR_SEX", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private Gender usr_sex;
    @Column(name = "USR_TEL", nullable = false, length = 15)
    private int usr_tel;
    @Column(name = "USR_USERNAME", nullable = false, length = 15, unique = true)
    private String usr_username;

    @OneToOne
    @JoinColumn(name = "CNT_ID", referencedColumnName = "CNT_ID",foreignKey = @ForeignKey(name="rf_cnt_id_usr"))
    private Country cnt_id;


    public Users() {
    }

    public Users(int usr_id, String usr_name, String usr_address, LocalDate usr_birthday, String usr_email, String usr_function, String usr_password, Gender usr_sex, int usr_tel, String usr_username, Country cnt_id) {
        this.usr_id = usr_id;
        this.usr_name = usr_name;
        this.usr_address = usr_address;
        this.usr_birthday = usr_birthday;
        this.usr_email = usr_email;
        this.usr_function = usr_function;
        this.usr_password = usr_password;
        this.usr_sex = usr_sex;
        this.usr_tel = usr_tel;
        this.usr_username = usr_username;
        this.cnt_id = cnt_id;
    }

    public int getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }

    public String getUsr_name() {
        return usr_name;
    }

    public void setUsr_name(String usr_name) {
        this.usr_name = usr_name;
    }

    public String getUsr_address() {
        return usr_address;
    }

    public void setUsr_address(String usr_address) {
        this.usr_address = usr_address;
    }

    public LocalDate getUsr_birthday() {
        return usr_birthday;
    }

    public void setUsr_birthday(LocalDate usr_birthday) {
        this.usr_birthday = usr_birthday;
    }

    public String getUsr_email() {
        return usr_email;
    }

    public void setUsr_email(String usr_email) {
        this.usr_email = usr_email;
    }

    public String getUsr_function() {
        return usr_function;
    }

    public void setUsr_function(String usr_function) {
        this.usr_function = usr_function;
    }

    public String getUsr_password() {
        return usr_password;
    }

    public void setUsr_password(String usr_password) {
        this.usr_password = usr_password;
    }

    public Gender getUsr_sex() {
        return usr_sex;
    }

    public void setUsr_sex(Gender usr_sex) {
        this.usr_sex = usr_sex;
    }

    public int getUsr_tel() {
        return usr_tel;
    }

    public void setUsr_tel(int usr_tel) {
        this.usr_tel = usr_tel;
    }

    public String getUsr_username() {
        return usr_username;
    }

    public void setUsr_username(String usr_username) {
        this.usr_username = usr_username;
    }

    public Country getCnt_id() {
        return cnt_id;
    }

    public void setCnt_id(Country cnt_id) {
        this.cnt_id = cnt_id;
    }
}
