package co.edu.cue.test.demo.mapping.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record PayDto(
        Long id,
        @Positive
        double mounter
) {
}
