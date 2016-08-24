package be.david.school.dbmodel;

import be.david.school.interfaces.DropDownMarker;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "GENRES" , uniqueConstraints = @UniqueConstraint(name="pk_genres", columnNames = {"GNR_ID"}))
public class Genres implements DropDownMarker {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GNR")
    @SequenceGenerator(name = "SEQ_GNR", sequenceName = "SEQ_GNR", initialValue = 1, allocationSize = 1)
    @Column (name = "GNR_ID")
    private int gnr_id;

    @Column(name = "GNR_DESCRIPTION", nullable = false, unique = true, length = 40)
    private String gnr_description;

    public Genres() {
    }

    public Genres(int gnr_id, String gnr_description) {
        this.gnr_id = gnr_id;
        this.gnr_description = gnr_description;
    }

    public int getGnr_id() {
        return gnr_id;
    }

    public void setGnr_id(int gnr_id) {
        this.gnr_id = gnr_id;
    }

    public String getGnr_description() {
        return gnr_description;
    }

    public void setGnr_description(String gnr_description) {
        this.gnr_description = gnr_description;
    }
}
