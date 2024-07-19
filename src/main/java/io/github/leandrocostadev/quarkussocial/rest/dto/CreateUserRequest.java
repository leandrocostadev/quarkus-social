package io.github.leandrocostadev.quarkussocial.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
public class CreateUserRequest {
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotNull(message = "age is required")
    @Positive(message = "age needs to be higher than 0")
    private  Integer age;
}
