package org.lucasnscr.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lucasnscr.events.PaymentProducer;
import org.lucasnscr.events.compensation.SeatEventProducer;
import org.lucasnscr.model.Payment;
import org.lucasnscr.model.Seat;
import org.lucasnscr.service.PaymentService;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.time.LocalDate;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class MakePaymentUseCase {

    private final PaymentService paymentService;

    private final PaymentProducer paymentProducer;

    private final SeatEventProducer seatEventProducer;

    public Payment makeAPayment(Seat seat) {
        log.info("Create payment  with seat  {}", seat.getId());
        var payment  = createPayment(seat);
        try {
            payment.setStatus("PAID");
            paymentService.savePayment(payment);
        }catch (Exception ex) {
            seatEventProducer.sendSeatEvent(payment.getSeat());
            return payment;
        }

        paymentProducer.sendPaymentEvent(payment);
        return payment;
    }

    private Payment createPayment(Seat seat) {
        Payment payment = new Payment();
        payment.setStatus("PAID");
        payment.setAmount(new BigDecimal(10));
        payment.setSeat(seat);
        payment.setUser(seat.getUser());
        payment.setDate(LocalDate.now());
        return payment;
    }
}
