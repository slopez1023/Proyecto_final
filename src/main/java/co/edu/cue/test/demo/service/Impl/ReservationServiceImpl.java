package co.edu.cue.test.demo.service.Impl;

import co.edu.cue.test.demo.Repositories.ReservationRepository;
import co.edu.cue.test.demo.mapping.dtos.ReservationDto;
import co.edu.cue.test.demo.mapping.mappers.ReservationMapper;
import co.edu.cue.test.demo.model.Reservation;
import co.edu.cue.test.demo.service.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;

    @Override
    public ReservationDto createReservation(ReservationDto reservationDto){
        Reservation reservation = ReservationMapper.mapFromDTO(reservationDto);
        Reservation savedReservation = reservationRepository.save(reservation);
        return ReservationMapper.mapFrom(savedReservation);
    }

    @Override
    public ReservationDto getReservationById(Long id){
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return reservation.map(ReservationMapper::mapFrom).orElse(null);
    }


    @Override
    public List<ReservationDto> getAllReservations(){
        return reservationRepository.findAll().stream()
                .map(ReservationMapper::mapFrom)
                .collect(Collectors.toList());
    }


    @Override
    public ReservationDto updateReservation(Long id, ReservationDto reservationDto){
        if (!reservationRepository.existsById(id)){
            return null;
        }
        Reservation reservation = ReservationMapper.mapFromDTO(reservationDto);
        reservation.setId(id);
        Reservation updatedReservation = reservationRepository.save(reservation);
        return ReservationMapper.mapFrom(updatedReservation);
    }

    @Override
    public boolean deleteReservation(Long id){
        if (!reservationRepository.existsById(id)){
            return false;
        }
        reservationRepository.deleteById(id);
        return true;
    }
}