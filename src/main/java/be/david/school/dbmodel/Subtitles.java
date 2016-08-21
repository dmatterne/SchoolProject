package be.david.school.dbmodel;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name="Subtitles", uniqueConstraints = @UniqueConstraint(name = "pr_subtitles", columnNames = {"SBT_ID"}))
public class Subtitles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SBT")
    @SequenceGenerator(name = "SEQ_SBT", sequenceName = "SEQ_SBT", initialValue = 1, allocationSize = 1)
    @Column(name = "SBT_ID")
    private int sbt_id;

    @Column(name = "SBT_NAME", nullable = false, unique = true, length = 10)
    private String sbt_name;

    public Subtitles() {
    }

    public Subtitles(int sbt_id, String sbt_name) {
        this.sbt_id = sbt_id;
        this.sbt_name = sbt_name;
    }

    public int getSbt_id() {
        return sbt_id;
    }

    public void setSbt_id(int sbt_id) {
        this.sbt_id = sbt_id;
    }

    public String getSbt_name() {
        return sbt_name;
    }

    public void setSbt_name(String sbt_name) {
        this.sbt_name = sbt_name;
    }
}
