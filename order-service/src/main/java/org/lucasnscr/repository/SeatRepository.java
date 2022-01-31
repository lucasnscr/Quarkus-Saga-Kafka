package org.lucasnscr.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.lucasnscr.model.Seat;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SeatRepository implements PanacheRepository<Seat> {


}
