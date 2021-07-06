package ru.leverx.leverxpetsspringbootproxy.client;

import ru.leverx.leverxpetsspringbootproxy.dto.PetDto;
import ru.leverx.leverxpetsspringbootproxy.dto.SwapPetsDto;

import java.io.IOException;
import java.util.List;

/**
 * @author Valeryia Zubrytskaya
 */
public interface HttpDestinationClientPets {

    PetDto httpGetPetById(Long id) throws IOException;

    List<PetDto> httpGetPets() throws IOException;

    void httpDeletePetById(Long id) throws IOException;

    PetDto httpCreatePet(PetDto petDto) throws IOException;

    PetDto httpUpdatePetById(Long id, PetDto petDto) throws IOException;

    void httpSwapPets(SwapPetsDto swapPetsDto) throws IOException;
}
