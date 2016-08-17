package be.david.school.repository;

import be.david.school.model.Cmp_features;
import be.david.school.model.Tickets;

import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public interface TicketsRepository {

    List<Tickets> findAllTickets();
}
