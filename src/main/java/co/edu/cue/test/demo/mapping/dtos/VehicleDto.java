package co.edu.cue.test.demo.mapping.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record VehicleDto(
        Long id,
        @NotNull @NotBlank @NotEmpty
        String type,
        @NotNull@NotBlank@NotEmpty
        String category,
        @Positive
        double price,
        boolean available
) {
}
