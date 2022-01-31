package org.lucasnscr.usecase;

import lombok.RequiredArgsConstructor;
import org.lucasnscr.events.compensation.SeatEventProducer;
import org.lucasnscr.service.PaymentService;

import javax.enterprise.context.ApplicationScoped;

@RequiredArgsConstructor
@ApplicationScoped
public class DeletePaymentUseCase {

    private final PaymentService paymentService;

    private final SeatEventProducer seatEventProducer;

    public void deletePayment(Long paymentId) {

        try {

            paymentService.deletePayment(paymentId);

        } catch (Exception ex) {

            seatEventProducer.sendSeatEvent(paymentService.findById(paymentId).getSeat());

        }
        //Refund money to user

    }
}
