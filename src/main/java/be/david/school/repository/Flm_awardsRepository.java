package be.david.school.repository;

import be.david.school.model.Customers;
import be.david.school.model.Flm_awards;

import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public interface Flm_awardsRepository {

    List<Flm_awards> findAllFlm_awards();
}
