package be.david.school.dbmodel;

import be.david.school.interfaces.DropDownMarker;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "LANGUAGES", uniqueConstraints = @UniqueConstraint(name = "pr_languages", columnNames = {"LNG_ID"}))
public class Languages implements DropDownMarker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LNG")
    @SequenceGenerator(name = "SEQ_LNG", sequenceName = "SEQ_LNG", initialValue = 1, allocationSize = 1)
    @Column(name = "LNG_ID", length = 3)
    private int lng_id;

    @Column (name = "LNG_LANGUAGE", nullable = false, unique = true, length = 30)
    private String lng_language;

    public Languages() {
    }

    public Languages(int lng_id, String lng_language) {
        this.lng_id = lng_id;
        this.lng_language = lng_language;
    }

    public int getLng_id() {
        return lng_id;
    }

    public void setLng_id(int lng_id) {
        this.lng_id = lng_id;
    }

    public String getLng_language() {
        return lng_language;
    }

    public void setLng_language(String lng_language) {
        this.lng_language = lng_language;
    }
}