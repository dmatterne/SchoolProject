package be.david.school.repository;

import be.david.school.dbmodel.Flm_reviews;

import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public interface Flm_reviewsRepository {

    List<Flm_reviews> findAllFlm_reviews();
}
