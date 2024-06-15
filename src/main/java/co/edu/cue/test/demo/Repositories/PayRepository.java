package co.edu.cue.test.demo.Repositories;

import co.edu.cue.test.demo.model.Pay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface PayRepository extends JpaRepository<Pay, Long> {
}
