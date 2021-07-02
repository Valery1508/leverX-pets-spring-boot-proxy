package ru.leverx.leverxpetsspringbootproxy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDto {

    protected Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private List<PetDto> pets;

}
