package co.edu.cue.test.demo.mapping.mappers;

import co.edu.cue.test.demo.mapping.dtos.ReservationDto;
import co.edu.cue.test.demo.model.Reservation;



public class ReservationMapper {
    public static ReservationDto mapFrom(Reservation reservation){
        return new ReservationDto(reservation.getId(),reservation.getInitialDate(),reservation.getFinalDate());
    }

    public static Reservation mapFromDTO(ReservationDto reservationDto){
        return Reservation.builder()
                .id(reservationDto.id())
                .initialDate(reservationDto.initialDate())
                .finalDate(reservationDto.finalDate())
                .build();
    }



}
