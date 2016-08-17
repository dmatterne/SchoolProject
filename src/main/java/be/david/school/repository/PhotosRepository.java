package be.david.school.repository;

import be.david.school.model.Customers;
import be.david.school.model.Photos;

import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public interface PhotosRepository {

    List<Photos> findAllPhotos();
}
