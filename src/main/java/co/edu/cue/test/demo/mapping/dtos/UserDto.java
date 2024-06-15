package co.edu.cue.test.demo.mapping.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public record UserDto(
        Long id,
        @NotNull@NotBlank@NotEmpty
        String name,
        @NotNull@NotBlank@NotEmpty
        String password,
        @NotNull@NotBlank@NotEmpty@Email
        String email

) {
}
