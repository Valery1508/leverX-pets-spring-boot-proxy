package ru.leverx.leverxpetsspringbootproxy.client;

import ru.leverx.leverxpetsspringbootproxy.dto.PersonRequestDto;
import ru.leverx.leverxpetsspringbootproxy.dto.PersonResponseDto;

import java.io.IOException;
import java.util.List;

/**
 * @author Valeryia Zubrytskaya
 */
public interface HttpDestinationClientPeople {

    PersonResponseDto httpGetPersonById(Long id) throws IOException;

    List<PersonResponseDto> httpGetPeople() throws IOException;

    void httpDeletePersonById(Long id) throws IOException;

    PersonResponseDto httpCreatePerson(PersonRequestDto personRequestDto) throws IOException;

    PersonResponseDto httpUpdatePersonById(Long id, PersonRequestDto personRequestDto) throws IOException;
}
