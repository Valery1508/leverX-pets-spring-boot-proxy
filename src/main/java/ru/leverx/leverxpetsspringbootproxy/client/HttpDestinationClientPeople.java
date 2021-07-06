package ru.leverx.leverxpetsspringbootproxy.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Repository;
import ru.leverx.leverxpetsspringbootproxy.dto.PersonRequestDto;
import ru.leverx.leverxpetsspringbootproxy.dto.PersonResponseDto;

import java.io.IOException;
import java.util.List;

import static ru.leverx.leverxpetsspringbootproxy.client.constants.DestinationConstants.GENERAL_URL;
import static ru.leverx.leverxpetsspringbootproxy.client.constants.DestinationConstants.HTTP_CLIENT;
import static ru.leverx.leverxpetsspringbootproxy.client.constants.DestinationConstants.OBJECT_MAPPER;

@Repository
public class HttpDestinationClientPeople {  //todo interface , naming "execute"

    public PersonResponseDto httpGetPersonById(Long id) throws IOException {

        HttpUriRequest uriRequest = new HttpGet(String.format("%s/people/%s", GENERAL_URL, id));    //todo вынести урлы в константы

        HttpResponse execute = HTTP_CLIENT.execute(uriRequest);
        HttpEntity entity = execute.getEntity();

        PersonResponseDto personResponseDto = OBJECT_MAPPER.readValue(EntityUtils.toString(entity), PersonResponseDto.class);
        return personResponseDto;
    }

    public List<PersonResponseDto> httpGetPeople() throws IOException {
        HttpUriRequest uriRequest = new HttpGet(String.format("%s/people", GENERAL_URL));

        HttpResponse execute = HTTP_CLIENT.execute(uriRequest);
        HttpEntity entity = execute.getEntity();

        List<PersonResponseDto> personResponseDtos = OBJECT_MAPPER.readValue(
                EntityUtils.toString(entity),
                new TypeReference<List<PersonResponseDto>>() {
        });
        return personResponseDtos;
    }

    public void httpDeletePersonById(Long id) throws IOException {
        HttpUriRequest uriRequest = new HttpDelete(String.format("%s/people/%s", GENERAL_URL, id));

        HTTP_CLIENT.execute(uriRequest);
    }

    public PersonResponseDto httpCreatePerson(PersonRequestDto personRequestDto) throws IOException {
        HttpPost uriRequest = new HttpPost(String.format("%s/people", GENERAL_URL));


        String body = OBJECT_MAPPER.writeValueAsString(personRequestDto);
        uriRequest.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        uriRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpResponse execute = HTTP_CLIENT.execute(uriRequest);
        HttpEntity entity = execute.getEntity();

        PersonResponseDto personResponseDto = OBJECT_MAPPER.readValue(EntityUtils.toString(entity), PersonResponseDto.class);
        return personResponseDto;
    }

    public PersonResponseDto httpUpdatePersonById(Long id, PersonRequestDto personRequestDto) throws IOException {
        HttpPut uriRequest = new HttpPut(String.format("%s/people/%s", GENERAL_URL, id));


        String body = OBJECT_MAPPER.writeValueAsString(personRequestDto);
        uriRequest.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        uriRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpResponse execute = HTTP_CLIENT.execute(uriRequest);
        HttpEntity entity = execute.getEntity();

        PersonResponseDto personResponseDto = OBJECT_MAPPER.readValue(EntityUtils.toString(entity), PersonResponseDto.class);
        return personResponseDto;
    }
}
