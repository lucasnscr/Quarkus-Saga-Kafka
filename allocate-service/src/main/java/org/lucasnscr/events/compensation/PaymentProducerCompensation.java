package org.lucasnscr.events.compensation;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.reactive.messaging.kafka.Record;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.lucasnscr.model.Payment;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class PaymentProducerCompensation {

    @Channel("payments-out")
    Emitter<Record<Long, String>> emitter;

    @SneakyThrows
    public void sendPaymentEvent(Payment payment) {
        log.info("Event sent {}", payment.getId());

        ObjectMapper objectMapper = new ObjectMapper();
        var paymentJson = objectMapper.writeValueAsString(payment);

        emitter.send(Record.of(payment.getId(), paymentJson))
                .whenComplete((success, failure) -> {
                    if (failure != null){
                        log.info("Message unprocessed failure " + failure.getMessage());
                    }else{
                        log.info("Message processed successfully");
                    }
                });
    }
}