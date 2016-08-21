package be.david.school.dbmodel;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "FORMATS", uniqueConstraints = @UniqueConstraint(name = "pk_formats", columnNames = {"FRM_ID"}))
public class Formats {

    @Id
    @Column(name = "FRM_ID")
    private int frm_id;

    @Column(name = "FRM_NAME", nullable = false, unique = true, length = 10)
    private String frm_name;

    public Formats() {
    }

    public Formats(int frm_id, String frm_name) {
        this.frm_id = frm_id;
        this.frm_name = frm_name;
    }

    public int getFrm_id() {
        return frm_id;
    }

    public void setFrm_id(int frm_id) {
        this.frm_id = frm_id;
    }

    public String getFrm_name() {
        return frm_name;
    }

    public void setFrm_name(String frm_name) {
        this.frm_name = frm_name;
    }
}