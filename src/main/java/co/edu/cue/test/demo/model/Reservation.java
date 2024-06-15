package co.edu.cue.test.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private LocalDate initialDate;
    private LocalDate finalDate;
    @OneToMany(mappedBy = "reservation")
    private List<Reservation>reservations;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", user=" + user +
                ", vehicle=" + vehicle +
                ", initialDate=" + initialDate +
                ", finalDate=" + finalDate +
                ", reservations=" + reservations +
                '}';
    }
}
