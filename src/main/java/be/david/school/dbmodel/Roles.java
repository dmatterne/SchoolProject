package be.david.school.dbmodel;

import be.david.school.interfaces.DropDownMarker;

import javax.persistence.*;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "ROLES", uniqueConstraints = @UniqueConstraint(name = "pk_roles", columnNames = {"RLS_ID"}))
public class Roles implements DropDownMarker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RLS")
    @SequenceGenerator(name = "SEQ_RLS", sequenceName = "SEQ_RLS", initialValue = 1, allocationSize = 1)
    @Column(name = "RLS_ID", length = 5)
    private int rls_id;

    @Column(name = "RLS_DESCRIPTION", length = 15, nullable = false)
    private String rls_description;

    public Roles() {
    }

    public Roles(String rls_description) {
        this.rls_description = rls_description;
    }

    public int getRls_id() {
        return rls_id;
    }

    public void setRls_id(int rls_id) {
        this.rls_id = rls_id;
    }

    public String getRls_description() {
        return rls_description;
    }

    public void setRls_description(String rls_description) {
        this.rls_description = rls_description;
    }
}