package co.edu.cue.test.demo.Repositories;

import co.edu.cue.test.demo.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Repository
@Service
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

}
