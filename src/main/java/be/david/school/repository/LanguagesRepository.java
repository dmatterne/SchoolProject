package be.david.school.repository;

import be.david.school.model.Distributors;
import be.david.school.model.Languages;

import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public interface LanguagesRepository {

    List<Languages> findAllLanguages();
}
