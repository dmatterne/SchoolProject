package be.david.school.repository;

import be.david.school.dbmodel.Flm_scores;

import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public interface Flm_scoresRepository {

    List<Flm_scores> findAllFlm_scores();
}
