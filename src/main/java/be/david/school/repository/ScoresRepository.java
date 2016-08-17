package be.david.school.repository;

import be.david.school.model.Customers;
import be.david.school.model.Scores;

import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public interface ScoresRepository {

    List<Scores> findAllScores();
}
