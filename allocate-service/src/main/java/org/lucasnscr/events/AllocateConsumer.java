package org.lucasnscr.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.reactive.messaging.kafka.Record;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.lucasnscr.model.Payment;
import org.lucasnscr.model.Seat;
import org.lucasnscr.usecase.AllocateUseCase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class AllocateConsumer {

    private final AllocateUseCase allocateUseCase;

    @SneakyThrows
    @Incoming("allocates-in")
    public void receive(Record<Long, String> record){
        log.info("Event received with key: {}", record.key());
        ObjectMapper objectMapper = new ObjectMapper();
        var payment = objectMapper.readValue(record.value(), Payment.class);
        var seat = payment.getSeat();
        allocateUseCase.updateSeat(seat);
    }
}
