package ru.leverx.leverxpetsspringbootproxy.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.leverx.leverxpetsspringbootproxy.client.HttpDestinationClientPeople;
import ru.leverx.leverxpetsspringbootproxy.dto.PersonRequestDto;
import ru.leverx.leverxpetsspringbootproxy.dto.PersonResponseDto;
import ru.leverx.leverxpetsspringbootproxy.service.PersonService;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService {

    private final HttpDestinationClientPeople httpDestinationClientPeople;

    @Override
    public PersonResponseDto getPersonById(long id) throws IOException {
        log.debug("(PROXY) getPersonById method started.");
        return httpDestinationClientPeople.HttpGetPersonById(id);
    }

    @Override
    public List<PersonResponseDto> getPeople() throws IOException {
        return httpDestinationClientPeople.HttpGetPeople();
    }

    @Override
    public PersonResponseDto createPerson(PersonRequestDto personRequestDto) {
        /*Person person = personMapper.toEntity(personRequestDto);
        Person savedPerson = personRepository.save(person);

        log.debug("Person with id={} was successfully saved!", savedPerson.getId());
        return getPersonById(savedPerson.getId());*/
        return null;
    }

    @Override
    public void deletePersonById(long id) throws IOException {
        /*if (checkPersonExistence(id)) {
            personRepository.deleteById(id);
            log.debug("Person with id={} was successfully deleted!", id);
        } else {
            throw new EntityNotFoundException(Person.class.getName(), id);
        }*/
        httpDestinationClientPeople.HttpDeletePersonById(id);
    }

    @Override
    public PersonResponseDto updatePerson(long id, PersonRequestDto personRequestDto) {
        /*if (!checkPersonExistence(id)) {
            throw new EntityNotFoundException(Person.class.getName(), id);
        }

        personRequestDto.setId(id);
        Person person = personMapper.toEntity(personRequestDto);

        Person savedPerson = personRepository.save(person);
        log.debug("Person with id={} was successfully saved!", savedPerson.getId());
        return personMapper.toDto(savedPerson);*/
        return null;
    }
}