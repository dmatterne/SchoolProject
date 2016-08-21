package be.david.school.dbmodel;

import javax.persistence.*;
import java.sql.Blob;

/**
 * Created by David on 17/08/2016.
 */
@Entity
@Table(name = "PHOTOS", uniqueConstraints = @UniqueConstraint(name = "pk_photos", columnNames = {"PHT_ID", "FLI_ID"}))
public class Photos {

    @EmbeddedId
    private PhotosId photosId;

    @Column(name = "PHT_PHOTO", nullable = true)
    private Blob pht_photo;
    @Column(name = "PHT_CONTENTTYPE", nullable = false, length = 50)
    private String pht_contenttype;
    @Column(name = "PHT_LENGTH", nullable = false, length = 15)
    private int pht_length;
    @Column(name = "PHT_NAME", nullable = false, length = 50)
    private String pht_name;

    public Photos() {
    }

    public Photos(PhotosId photosId, Blob pht_photo, String pht_contenttype, int pht_length, String pht_name) {
        this.photosId = photosId;
        this.pht_photo = pht_photo;
        this.pht_contenttype = pht_contenttype;
        this.pht_length = pht_length;
        this.pht_name = pht_name;
    }

    public PhotosId getPhotosId() {
        return photosId;
    }

    public void setPhotosId(PhotosId photosId) {
        this.photosId = photosId;
    }

    public Blob getPht_photo() {
        return pht_photo;
    }

    public void setPht_photo(Blob pht_photo) {
        this.pht_photo = pht_photo;
    }

    public String getPht_contenttype() {
        return pht_contenttype;
    }

    public void setPht_contenttype(String pht_contenttype) {
        this.pht_contenttype = pht_contenttype;
    }

    public int getPht_length() {
        return pht_length;
    }

    public void setPht_length(int pht_length) {
        this.pht_length = pht_length;
    }

    public String getPht_name() {
        return pht_name;
    }

    public void setPht_name(String pht_name) {
        this.pht_name = pht_name;
    }
}

//    CREATE TABLE PHOTOS
//        (pht_id			NUMBER(2)
//                                                ,fli_id			NUMBER(15)	CONSTRAINT rf_fli_id6		REFERENCES FLM_INFO
//                                                        ,pht_photo		BLOB		CONSTRAINT nn_pht_photo		NOT NULL
//                                                        ,pht_contenttype        VARCHAR2(50)    CONSTRAINT nn_pht_content       NOT NULL
//                                                        ,pht_length             NUMBER(15)      CONSTRAINT nn_pht_length        NOT NULL
//                                                        ,pht_name               VARCHAR2(50)    CONSTRAINT nn_pht_name          NOT NULL
//                                                        ,CONSTRAINT pr_photos PRIMARY KEY(pht_id,fli_id)
//                                                        )
//                                                        /
//
//