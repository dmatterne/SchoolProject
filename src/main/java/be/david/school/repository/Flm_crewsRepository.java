package be.david.school.repository;

import be.david.school.dbmodel.Flm_crews;

import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public interface Flm_crewsRepository {

    List<Flm_crews> findAllFlm_crews();
}
