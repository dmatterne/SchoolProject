package be.david.school.dbmodel;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name="SOUNDS", uniqueConstraints = @UniqueConstraint(name="pr_sounds", columnNames = {"SND_ID"}))
public class Sounds {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SND")
    @SequenceGenerator(name = "SEQ_SND" , sequenceName = "SEQ_SND", initialValue = 1, allocationSize = 1)
    @Column(name="SND_ID")
    private int snd_id;

    @Column(name="SND_NAME", nullable = false, unique = true, length = 10)
    private String snd_name;

    public Sounds() {
    }

    public Sounds(String snd_name) {
        this.snd_name = snd_name;
    }

    public int getSnd_id() {
        return snd_id;
    }

    public void setSnd_id(int snd_id) {
        this.snd_id = snd_id;
    }

    public String getSnd_name() {
        return snd_name;
    }

    public void setSnd_name(String snd_name) {
        this.snd_name = snd_name;
    }
}
