package ru.leverx.leverxpetsspringbootproxy.service;

import ru.leverx.leverxpetsspringbootproxy.dto.PetDto;

import java.io.IOException;
import java.util.List;

public interface PetService {

    PetDto getPetById(long id) throws IOException;

    List<PetDto> getPets() throws IOException;

    void deletePetById(long id) throws IOException;

    PetDto updatePet(long id, PetDto petDto);

    PetDto createPet(PetDto petDto);

}
