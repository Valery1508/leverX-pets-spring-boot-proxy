package ru.leverx.leverxpetsspringbootproxy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDto {

    protected Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotNull
    private long personId;
}
