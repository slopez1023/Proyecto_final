package co.edu.cue.test.demo.mapping.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record ReservationDto(
        Long id,
        @NotNull @NotBlank @NotEmpty
        LocalDate initialDate,
        @NotNull @NotBlank @NotEmpty
        LocalDate finalDate
) {
}
