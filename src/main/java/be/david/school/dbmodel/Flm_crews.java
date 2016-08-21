package be.david.school.dbmodel;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity //@IdClass(Flm_crewsId.class)
@Table(name = "FLM_CREWS", uniqueConstraints = @UniqueConstraint(name = "pk_flm_crews", columnNames = {"RLS_ID", "FLI_ID", "PRS_ID"}))
public class Flm_crews {

    @EmbeddedId
    private Flm_crewsId id;

    @Column(name = "FLC_CHARACTER", length = 10)
    private String flc_character;

    @Column(name = "FLC_AWARD", length = 20)
    private String flc_award;

    public Flm_crews() {
    }

    public Flm_crews(Flm_crewsId id, String flc_character, String flc_award) {
        this.id = id;
        this.flc_character = flc_character;
        this.flc_award = flc_award;
    }

    public Flm_crewsId getId() {
        return id;
    }

    public void setId(Flm_crewsId id) {
        this.id = id;
    }

    public String getFlc_character() {
        return flc_character;
    }

    public void setFlc_character(String flc_character) {
        this.flc_character = flc_character;
    }

    public String getFlc_award() {
        return flc_award;
    }

    public void setFlc_award(String flc_award) {
        this.flc_award = flc_award;
    }
}


