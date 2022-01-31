package org.lucasnscr.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.lucasnscr.model.Payment;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaymentRepository implements PanacheRepository<Payment> {
}
