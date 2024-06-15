package co.edu.cue.test.demo.service;

// ReservationService.java
import co.edu.cue.test.demo.mapping.dtos.ReservationDto;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    ReservationDto createReservation(ReservationDto reservationDto);
    ReservationDto getReservationById(Long id);
    List<ReservationDto> getAllReservations();
    ReservationDto updateReservation(Long id, ReservationDto reservationDto);
    boolean deleteReservation(Long id);
}

