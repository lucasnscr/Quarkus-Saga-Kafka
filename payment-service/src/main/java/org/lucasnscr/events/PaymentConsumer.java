package org.lucasnscr.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.reactive.messaging.kafka.Record;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.lucasnscr.model.Seat;
import org.lucasnscr.usecase.MakePaymentUseCase;

import javax.enterprise.context.ApplicationScoped;


/**
 * This class receive a message with the seat that has been selected.
 * After recive the event this class is going to save and send a message to update the status of the seat.
 */
@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer {

    private final MakePaymentUseCase makePaymentUseCase;

    @SneakyThrows
    @Incoming("payments-in")
    public void receive(Record<Long, String> record) {

        log.info("record es: {}", record.key());

        ObjectMapper objectMapper = new ObjectMapper();

        var seat = objectMapper.readValue(record.value(), Seat.class);

        makePaymentUseCase.makeAPayment(seat);

    }

}
