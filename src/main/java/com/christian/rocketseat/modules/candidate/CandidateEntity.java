package com.christian.rocketseat.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateEntity {

    private UUID id;
    private String name;

    @Pattern(regexp = "^(?!\\s*$).+", message = "O campo não deve conter espaço")
    private String username;

    @Email(message = "O campo deve conter um e-mail válido!")
    private String email;

    @Length(min = 10, max = 100)
    private String password;
    private String description;
    private String curriculum;
}
