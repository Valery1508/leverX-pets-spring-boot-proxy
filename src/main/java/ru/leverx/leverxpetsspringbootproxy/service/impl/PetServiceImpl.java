package ru.leverx.leverxpetsspringbootproxy.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.leverx.leverxpetsspringbootproxy.client.HttpDestinationClientPets;
import ru.leverx.leverxpetsspringbootproxy.dto.PetDto;
import ru.leverx.leverxpetsspringbootproxy.dto.SwapPetsDto;
import ru.leverx.leverxpetsspringbootproxy.service.PetService;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class PetServiceImpl implements PetService {

    private final HttpDestinationClientPets httpDestinationClientPets;

    @Override
    public List<PetDto> getPets() throws IOException {
        return httpDestinationClientPets.httpGetPets();
    }

    @Override
    public PetDto getPetById(long id) throws IOException {
        return httpDestinationClientPets.httpGetPetById(id);
    }

    @Override
    public PetDto createPet(PetDto petDto) throws IOException {
        return httpDestinationClientPets.httpCreatePet(petDto);
    }

    @Override
    public void deletePetById(long id) throws IOException {
        httpDestinationClientPets.httpDeletePetById(id);
    }

    @Override
    public PetDto updatePet(long id, PetDto petDto) throws IOException {
        return httpDestinationClientPets.httpUpdatePetById(id, petDto);
    }

    @Override
    public void swapPets(SwapPetsDto swapPetsDto) throws IOException {
        httpDestinationClientPets.httpSwapPets(swapPetsDto);
    }
}