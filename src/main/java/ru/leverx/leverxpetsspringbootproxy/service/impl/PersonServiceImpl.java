package ru.leverx.leverxpetsspringbootproxy.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.leverx.leverxpetsspringbootproxy.client.HttpDestinationClientPeople;
import ru.leverx.leverxpetsspringbootproxy.client.impl.HttpDestinationClientPeopleImpl;
import ru.leverx.leverxpetsspringbootproxy.dto.PersonRequestDto;
import ru.leverx.leverxpetsspringbootproxy.dto.PersonResponseDto;
import ru.leverx.leverxpetsspringbootproxy.service.PersonService;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final HttpDestinationClientPeople httpDestinationClientPeople;

    @Override
    public PersonResponseDto getPersonById(long id, String token) throws IOException {
        return httpDestinationClientPeople.httpGetPersonById(id, token);
    }

    @Override
    public List<PersonResponseDto> getPeople(String token) throws IOException {
        return httpDestinationClientPeople.httpGetPeople(token);
    }

    @Override
    public PersonResponseDto createPerson(PersonRequestDto personRequestDto) throws IOException {
        return httpDestinationClientPeople.httpCreatePerson(personRequestDto);
    }

    @Override
    public void deletePersonById(long id) throws IOException {
        httpDestinationClientPeople.httpDeletePersonById(id);
    }

    @Override
    public PersonResponseDto updatePerson(long id, PersonRequestDto personRequestDto) throws IOException {
        return httpDestinationClientPeople.httpUpdatePersonById(id, personRequestDto);
    }
}