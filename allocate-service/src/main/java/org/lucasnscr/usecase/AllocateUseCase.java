package org.lucasnscr.usecase;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lucasnscr.events.AllocateProducer;
import org.lucasnscr.events.compensation.PaymentProducerCompensation;
import org.lucasnscr.model.Seat;
import org.lucasnscr.service.SeatService;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class AllocateUseCase {

    private final SeatService seatService;

    private final PaymentProducerCompensation paymentProducerCompensation;

    private final AllocateProducer allocateProducer;


    public Seat updateSeat(Seat seat) {

        log.info("Save seat {}", seat.toString());

        try {
            seatService.updateSeat(seat);

            allocateProducer.sendSeatEvent(seat);

        } catch (Exception ex) {

            paymentProducerCompensation.sendPaymentEvent(seat.getPayment());

        }

        return seatService.findById(seat.getId());

    }


}
