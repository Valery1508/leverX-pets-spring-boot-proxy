package ru.leverx.leverxpetsspringbootproxy.service;


import ru.leverx.leverxpetsspringbootproxy.dto.PersonRequestDto;
import ru.leverx.leverxpetsspringbootproxy.dto.PersonResponseDto;

import java.io.IOException;
import java.util.List;

public interface PersonService {

    PersonResponseDto getPersonById(long id) throws IOException;

    List<PersonResponseDto> getPeople() throws IOException;

    void deletePersonById(long id);

    PersonResponseDto createPerson(PersonRequestDto personRequestDto);

    PersonResponseDto updatePerson(long id, PersonRequestDto personRequestDto);
}
