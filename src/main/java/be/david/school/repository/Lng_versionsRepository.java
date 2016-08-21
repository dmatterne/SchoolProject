package be.david.school.repository;

import be.david.school.dbmodel.Lng_versions;

import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public interface Lng_versionsRepository {

    List<Lng_versions> findAllLng_versions();
}
