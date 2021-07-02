package ru.leverx.leverxpetsspringbootproxy.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Repository;
import ru.leverx.leverxpetsspringbootproxy.dto.PersonResponseDto;

import java.io.IOException;
import java.util.List;

import static ru.leverx.leverxpetsspringbootproxy.client.constants.DestinationConstants.GENERAL_URL;
import static ru.leverx.leverxpetsspringbootproxy.client.constants.DestinationConstants.HTTP_CLIENT;

@Repository
public class HttpDestinationClientPeople {

    public PersonResponseDto HttpGetPersonById(Long id) throws IOException {

        HttpUriRequest uriRequest = new HttpGet(String.format("%s/people/%s", GENERAL_URL, id));

        HttpResponse execute = HTTP_CLIENT.execute(uriRequest);
        HttpEntity entity = execute.getEntity();

        ObjectMapper objectMapper = new ObjectMapper();
        PersonResponseDto personResponseDto = objectMapper.readValue(EntityUtils.toString(entity), PersonResponseDto.class);
        return personResponseDto;
    }

    public List<PersonResponseDto> HttpGetPeople() throws IOException {
        HttpUriRequest uriRequest = new HttpGet(String.format("%s/people", GENERAL_URL));

        HttpResponse execute = HTTP_CLIENT.execute(uriRequest);
        HttpEntity entity = execute.getEntity();

        ObjectMapper objectMapper = new ObjectMapper();
        List<PersonResponseDto> personResponseDtos = objectMapper.readValue(EntityUtils.toString(entity), new TypeReference<List<PersonResponseDto>>() {});
        return personResponseDtos;
    }
}
