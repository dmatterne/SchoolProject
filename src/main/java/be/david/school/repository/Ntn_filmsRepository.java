package be.david.school.repository;

import be.david.school.model.Lng_versions;
import be.david.school.model.Ntn_films;

import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public interface Ntn_filmsRepository {

    List<Ntn_films> findAllNtn_films();
}
