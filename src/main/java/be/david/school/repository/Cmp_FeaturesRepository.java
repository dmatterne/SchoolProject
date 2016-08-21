package be.david.school.repository;

import be.david.school.dbmodel.Cmp_features;

import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public interface Cmp_FeaturesRepository {

    List<Cmp_features> findAllCmpFeatures();
    List<Cmp_features> findAllCmpLanguages();
}
