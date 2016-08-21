package be.david.school.dbmodel;

import javax.persistence.*;
import java.sql.Blob;

/**
 * Created by David on 15/08/2016.
 */
@Entity
@Table(name = "TRAILERS", uniqueConstraints = @UniqueConstraint(name = "pk_trailers", columnNames = {"TRL_ID","FLI_ID"}))
public class Trailers {

    @EmbeddedId
    private TrailersId trailersId;

    @Column(name = "TRL_TRAILER", nullable = true)
    private Blob trl_trailer;

    @Column(name = "TRL_LENGTH", length = 15, nullable = false)
    private int trl_length;

    @Column(name = "TRL_NAME", length = 50, nullable = false)
    private String trl_name;


    public Trailers() {
    }

    public Trailers(TrailersId trailersId, Blob trl_trailer, int trl_length, String trl_name) {
        this.trailersId = trailersId;
        this.trl_trailer = trl_trailer;
        this.trl_length = trl_length;
        this.trl_name = trl_name;
    }

    public TrailersId getTrailersId() {
        return trailersId;
    }

    public void setTrailersId(TrailersId trailersId) {
        this.trailersId = trailersId;
    }

    public Blob getTrl_trailer() {
        return trl_trailer;
    }

    public void setTrl_trailer(Blob trl_trailer) {
        this.trl_trailer = trl_trailer;
    }

    public int getTrl_length() {
        return trl_length;
    }

    public void setTrl_length(int trl_length) {
        this.trl_length = trl_length;
    }

    public String getTrl_name() {
        return trl_name;
    }

    public void setTrl_name(String trl_name) {
        this.trl_name = trl_name;
    }
}



