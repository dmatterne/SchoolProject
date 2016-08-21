package be.david.school.repository;

import be.david.school.dbmodel.Customers;

import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public interface CustomersRepository {

    List<Customers> findAllCustomers();
}
