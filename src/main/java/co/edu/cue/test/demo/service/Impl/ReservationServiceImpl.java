package co.edu.cue.test.demo.service.Impl;

import co.edu.cue.test.demo.Repositories.ReservationRepository;
import co.edu.cue.test.demo.mapping.dtos.ReservationDto;
import co.edu.cue.test.demo.mapping.mappers.ReservationMapper;
import co.edu.cue.test.demo.model.Reservation;
import co.edu.cue.test.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Implementación del servicio para gestionar reservaciones.
 */
@Service
@Validated
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * Crea una nueva reservación.
     *
     * @param reservationDto DTO de la reservación a crear.
     * @return DTO de la reservación creada.
     *
     * Este método toma un objeto ReservationDto, lo convierte en una entidad Reservation,
     * guarda la entidad en el repositorio y devuelve un DTO con los datos de la reservación guardada.
     */
    @Override
    public ReservationDto createReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setInitialDate(reservationDto.initialDate());
        reservation.setFinalDate(reservationDto.finalDate());

        Reservation savedReservation = reservationRepository.save(reservation);
        return new ReservationDto(savedReservation.getId(), savedReservation.getInitialDate(), savedReservation.getFinalDate());
    }

    /**
     * Obtiene una reservación por su ID.
     *
     * @param id ID de la reservación a buscar.
     * @return DTO de la reservación encontrada, o null si no se encuentra.
     *
     * Este método busca una reservación en el repositorio por su ID y, si la encuentra,
     * devuelve un DTO con los datos de la reservación. Si no la encuentra, devuelve null.
     */
    @Override
    public ReservationDto getReservationById(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return reservation.map(r -> new ReservationDto(r.getId(), r.getInitialDate(), r.getFinalDate())).orElse(null);
    }

    /**
     * Obtiene todas las reservaciones.
     *
     * @return Lista de DTOs de todas las reservaciones.
     *
     * Este método obtiene todas las entidades Reservation del repositorio, las convierte en DTOs
     * y devuelve una lista de estos DTOs.
     */
    @Override
    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(r -> new ReservationDto(r.getId(), r.getInitialDate(), r.getFinalDate()))
                .collect(Collectors.toList());
    }

    /**
     * Actualiza una reservación existente.
     *
     * @param id ID de la reservación a actualizar.
     * @param reservationDto DTO de la reservación con los nuevos datos.
     * @return DTO de la reservación actualizada, o null si no se encuentra.
     *
     * Este método busca una reservación por su ID. Si la encuentra, actualiza los datos de la reservación
     * con la información proporcionada en el DTO, guarda la entidad actualizada en el repositorio
     * y devuelve un DTO con los datos de la reservación actualizada. Si no la encuentra, devuelve null.
     */
    @Override
    public ReservationDto updateReservation(Long id, ReservationDto reservationDto) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setInitialDate(reservationDto.initialDate());
            reservation.setFinalDate(reservationDto.finalDate());
            Reservation updatedReservation = reservationRepository.save(reservation);
            return new ReservationDto(updatedReservation.getId(), updatedReservation.getInitialDate(), updatedReservation.getFinalDate());
        }
        return null;
    }

    /**
     * Elimina una reservación por su ID.
     *
     * @param id ID de la reservación a eliminar.
     * @return true si la reservación fue eliminada, false si no se encuentra.
     *
     * Este método verifica si una reservación existe por su ID. Si existe, la elimina del repositorio
     * y devuelve true. Si no existe, devuelve false.
     */
    @Override
    public boolean deleteReservation(Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
