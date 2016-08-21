package be.david.school.repository;

import be.david.school.dbmodel.Distributors;

import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public interface DistributorsRepository {

    List<Distributors> findAllDistributors();
}
