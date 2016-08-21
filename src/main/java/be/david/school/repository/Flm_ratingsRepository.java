package be.david.school.repository;

import be.david.school.dbmodel.Flm_ratings;

import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public interface Flm_ratingsRepository {

    List<Flm_ratings> findAllFLMRatings();
}
