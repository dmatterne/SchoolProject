package be.david.school.dbmodel;

import be.david.school.interfaces.DropDownMarker;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "SCORES", uniqueConstraints = @UniqueConstraint(name = "pk_scores", columnNames = {"SCR_ID"}))
public class Scores implements DropDownMarker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SCR")
    @SequenceGenerator(name = "SEQ_SCR", sequenceName = "SEQ_SCR", initialValue = 1, allocationSize = 1)
    @Column(name = "SCR_ID", length = 3)
    private int scr_id;
    @Column(name = "SCR_DESCRIPTION", length = 50, nullable = false, unique = true)
    private String scr_description;

    public Scores() {
    }

    public Scores(String scr_description) {
        this.scr_description = scr_description;
    }

    public int getScr_id() {
        return scr_id;
    }

    public void setScr_id(int scr_id) {
        this.scr_id = scr_id;
    }

    public String getScr_description() {
        return scr_description;
    }

    public void setScr_description(String scr_description) {
        this.scr_description = scr_description;
    }
}