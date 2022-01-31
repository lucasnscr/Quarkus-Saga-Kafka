package org.lucasnscr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lucasnscr.events.PaymentProducer;
import org.lucasnscr.model.Seat;
import org.lucasnscr.repository.SeatRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.REQUIRES_NEW;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class SeatService {

    @Inject
    private final SeatRepository seatRepository;

    @Inject
    private final PaymentProducer paymentProducer;

    @Transactional(REQUIRES_NEW)
    public Seat blockSeat(Seat seat) {

        log.info("Block a  seat ", seat.toString());

        seat.setStatus("BLOCKED");

        seatRepository.persistAndFlush(seat);

        return seat;
    }


}
