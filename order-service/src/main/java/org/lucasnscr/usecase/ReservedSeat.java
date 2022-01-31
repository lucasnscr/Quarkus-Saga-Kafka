package org.lucasnscr.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lucasnscr.events.SeatEventProducer;
import org.lucasnscr.model.Seat;
import org.lucasnscr.repository.UserRepository;
import org.lucasnscr.service.SeatService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class ReservedSeat {

    @Inject
    private final SeatService seatService;

    @Inject
    private SeatEventProducer seatEventProducer;

    private final UserRepository userRepository;

    public Seat reservedSeat(Seat seat) {
        log.info("Update seat {}", seat.getId());
        var seatToSave = seatService.lockSeat(seat.getId());
        seatEventProducer.sendOrder(seatToSave);
        return seatToSave;
    }
}
