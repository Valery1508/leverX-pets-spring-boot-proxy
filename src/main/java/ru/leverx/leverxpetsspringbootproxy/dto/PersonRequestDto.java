package ru.leverx.leverxpetsspringbootproxy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequestDto {

    protected Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
