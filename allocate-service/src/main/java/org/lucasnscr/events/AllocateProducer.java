package org.lucasnscr.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.reactive.messaging.kafka.Record;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.lucasnscr.model.Seat;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Slf4j
public class AllocateProducer {

    @Channel("seats-out")
    Emitter<Record<Long, String>> emitter;

    @SneakyThrows
    public void sendSeatEvent(Seat seat){
        log.info("Event sent seat with id{}", seat.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        var seatJson = objectMapper.writeValueAsString(seat);

        emitter.send(Record.of(seat.getId(), seatJson))
        .whenComplete((sucess, failure) -> {
            if (failure != null){
                log.info("Message unprocessed failure " + failure.getMessage());
            }else{
                log.info("Message processed successfully");
            }

        });
    }

}
