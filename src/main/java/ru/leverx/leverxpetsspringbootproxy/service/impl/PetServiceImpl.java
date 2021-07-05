package ru.leverx.leverxpetsspringbootproxy.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.leverx.leverxpetsspringbootproxy.client.HttpDestinationClientPets;
import ru.leverx.leverxpetsspringbootproxy.dto.PetDto;
import ru.leverx.leverxpetsspringbootproxy.service.PetService;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class PetServiceImpl implements PetService {

    private final HttpDestinationClientPets httpDestinationClientPets;

    @Override
    public List<PetDto> getPets() throws IOException {
        return httpDestinationClientPets.httpGetPets();
    }

    @Override
    public PetDto getPetById(long id) throws IOException {
        log.debug("(PROXY)getPetById method started.");
        return httpDestinationClientPets.httpGetPetById(id);
    }

    @Override
    public PetDto createPet(PetDto petDto) {
        /*long personId = petDto.getPersonId();

        if (!personService.checkPersonExistence(personId)) {
            throw new EntityNotFoundException(Person.class.getName(), personId);
        }

        Pet pet = petMapper.toEntity(petDto);
        Pet savedPet = petRepository.save(pet);
        log.debug("Pet with id={} was successfully saved!", savedPet.getId());
        return getPetById(savedPet.getId());*/
        return null;
    }

    @Override
    public void deletePetById(long id) throws IOException {
        httpDestinationClientPets.httpDeletePetById(id);
    }

    @Override
    public PetDto updatePet(long id, PetDto petDto) {
        /*if (!checkPetExistence(id)) {
            throw new EntityNotFoundException(Pet.class.getName(), id);
        } else {
            long personId = petDto.getPersonId();
            if (!personService.checkPersonExistence(personId)) {
                throw new EntityNotFoundException(Person.class.getName(), personId);
            }
        }

        petDto.setId(id);
        Pet pet = petMapper.toEntity(petDto);

        Pet savedPet = petRepository.save(pet);
        log.debug("Pet with id={} was successfully saved!", savedPet.getId());
        return petMapper.toDto(savedPet);*/
        return null;
    }

    public void updatePersonId(long personId, long petId) {
        /*log.debug("personId({}) for Pet with id={} was successfully updated!", personId, petId);
        petRepository.updatePersonId(personId, petId);*/
    }

    /*@Override
    public void checkOwnership(Long personId, Long petId) {
        Optional<Person> person = petRepository.findPersonByPetId(petId);
        if (!personId.equals(person.get().getId())) {
            throw new OwnershipException(personId, petId);
        }
    }

    private List<PetDto> toDtos(List<Pet> pets) {
        return pets.stream()
                .map(petMapper::toDto)
                .collect(toList());
    }*/
}