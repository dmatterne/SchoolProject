package be.david.school.dbmodel;



import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "DISTRIBUTORS", uniqueConstraints = @UniqueConstraint(name="pr_dst_id", columnNames = {"DST_ID"}))
public class Distributors {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DST")
    @SequenceGenerator(name = "SEQ_DST", sequenceName = "SEQ_DST", initialValue = 1, allocationSize = 1)
    @Column (name = "DST_ID", length = 3)
    private int dst_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CNT_ID", referencedColumnName = "CNT_ID",foreignKey = @ForeignKey(name="rf_cnt_id"))
    private Country cnt_id;

    @Column(name = "DST_NAME", nullable = false, length = 30 )
    private String dst_name;
    @Column(name = "DST_ADDRESS", nullable = false, length = 50 )
    private String dst_address;
    @Column(name = "DST_ZIPCODE", nullable = false, length = 15)
    private String dst_zipcode;
    @Column(name = "DST_CITY", nullable = false, length = 50 )
    private String dst_city;
    @Column(name = "DST_PHONE", nullable = false, length = 15 )
    private int dst_phone;
    @Column(name = "DST_FAX", nullable = false, length = 15 )
    private int dst_fax;
    @Column(name = "DST_EMAIL", nullable = false, length = 50)
    private String dst_email;
    @Column(name = "DST_CONTACT", nullable = false, length = 30)
    private String dst_contact;

    public Distributors() {
    }

    public Distributors(Country cnt_id, String dst_name, String dst_address, String dst_zipcode, String dst_city, int dst_phone, int dst_fax, String dst_email, String dst_contact) {
        this.cnt_id = cnt_id;
        this.dst_name = dst_name;
        this.dst_address = dst_address;
        this.dst_zipcode = dst_zipcode;
        this.dst_city = dst_city;
        this.dst_phone = dst_phone;
        this.dst_fax = dst_fax;
        this.dst_email = dst_email;
        this.dst_contact = dst_contact;
    }

    public int getDst_id() {
        return dst_id;
    }

    public void setDst_id(int dst_id) {
        this.dst_id = dst_id;
    }

    public Country getCnt_id() {
        return cnt_id;
    }

    public void setCnt_id(Country cnt_id) {
        this.cnt_id = cnt_id;
    }

    public String getDst_name() {
        return dst_name;
    }

    public void setDst_name(String dst_name) {
        this.dst_name = dst_name;
    }

    public String getDst_address() {
        return dst_address;
    }

    public void setDst_address(String dst_address) {
        this.dst_address = dst_address;
    }

    public String getDst_zipcode() {
        return dst_zipcode;
    }

    public void setDst_zipcode(String dst_zipcode) {
        this.dst_zipcode = dst_zipcode;
    }

    public String getDst_city() {
        return dst_city;
    }

    public void setDst_city(String dst_city) {
        this.dst_city = dst_city;
    }

    public int getDst_phone() {
        return dst_phone;
    }

    public void setDst_phone(int dst_phone) {
        this.dst_phone = dst_phone;
    }

    public int getDst_fax() {
        return dst_fax;
    }

    public void setDst_fax(int dst_fax) {
        this.dst_fax = dst_fax;
    }

    public String getDst_email() {
        return dst_email;
    }

    public void setDst_email(String dst_email) {
        this.dst_email = dst_email;
    }

    public String getDst_contact() {
        return dst_contact;
    }

    public void setDst_contact(String dst_contact) {
        this.dst_contact = dst_contact;
    }
}

