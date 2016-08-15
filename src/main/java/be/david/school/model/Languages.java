package be.david.school.model;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "LANGUAGES", uniqueConstraints = @UniqueConstraint(name = "pr_languages", columnNames = {"LNG_ID"}))
public class Languages {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LNG")
    @SequenceGenerator(name = "SEQ_LNG", sequenceName = "SEQ_LNG", initialValue = 1, allocationSize = 1)
    @Column(name = "LNG_ID", length = 3)
    private int lng_id;

    @Column (name = "LNG_LANGUAGE", nullable = false, unique = true, length = 30)
    private String lng_language;
}