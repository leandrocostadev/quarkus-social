package io.github.leandrocostadev.quarkussocial.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePostRequest {

    @NotNull
    private String text;
}
