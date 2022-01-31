package org.lucasnscr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lucasnscr.events.compensation.PaymentProducerCompensation;
import org.lucasnscr.model.Payment;
import org.lucasnscr.repository.PaymentRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private  final PaymentProducerCompensation paymentProducerCompensation;

    @Transactional
    public Payment savePayment(Payment payment){
        try {
            paymentRepository.persistAndFlush(payment);
        }catch (Exception exception){
            paymentProducerCompensation.sendPaymentEvent(payment);
        }
        return payment;
    }

    public void deletePayment(Long paymentId){
        try{
            paymentRepository.deleteById(paymentId);
        }catch (Exception exception){
            paymentProducerCompensation.sendPaymentEvent(paymentRepository.findById(paymentId));
        }
    }

}
