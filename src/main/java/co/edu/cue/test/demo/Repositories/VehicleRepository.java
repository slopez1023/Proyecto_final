package co.edu.cue.test.demo.Repositories;

import co.edu.cue.test.demo.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Repository
@Service
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
