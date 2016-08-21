package be.david.school.repository;

import be.david.school.dbmodel.Flm_info;

import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public interface Flm_infoRepository {

    List<Flm_info> findAllFlm_info();
}
