package be.david.school.repository;

import be.david.school.dbmodel.Org_films;

import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public interface Org_filmsRepository {

    List<Org_films> findAllOrg_films();
}
