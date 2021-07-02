package ru.leverx.leverxpetsspringbootproxy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwapPetsDto {

    @NotNull
    private long firstPersonId;

    @NotNull
    private long secondPersonId;

    @NotNull
    private long firstPetId;

    @NotNull
    private long secondPetId;
}
