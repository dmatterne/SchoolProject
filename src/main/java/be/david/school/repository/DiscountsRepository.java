package be.david.school.repository;

import be.david.school.model.Cmp_features;
import be.david.school.model.Discounts;

import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public interface DiscountsRepository {

    List<Discounts> findAllDiscounts();
}
