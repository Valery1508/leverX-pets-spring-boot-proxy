package ru.leverx.leverxpetsspringbootproxy.service;


import ru.leverx.leverxpetsspringbootproxy.dto.PersonRequestDto;
import ru.leverx.leverxpetsspringbootproxy.dto.PersonResponseDto;

import java.io.IOException;
import java.util.List;

public interface PersonService {

    PersonResponseDto getPersonById(long id, String token) throws IOException;

    List<PersonResponseDto> getPeople(String token) throws IOException;

    void deletePersonById(long id) throws IOException;

    PersonResponseDto createPerson(PersonRequestDto personRequestDto) throws IOException;

    PersonResponseDto updatePerson(long id, PersonRequestDto personRequestDto) throws IOException;
}
