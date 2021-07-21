package ru.leverx.leverxpetsspringbootproxy.client.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sap.cloud.sdk.cloudplatform.security.principal.PrincipalAccessor;
import com.sap.cloud.sdk.cloudplatform.security.principal.PrincipalFacade;
import com.sap.cloud.sdk.cloudplatform.security.principal.ScpCfPrincipalFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import ru.leverx.leverxpetsspringbootproxy.client.HttpDestinationClientPeople;
import ru.leverx.leverxpetsspringbootproxy.dto.PersonRequestDto;
import ru.leverx.leverxpetsspringbootproxy.dto.PersonResponseDto;
import ru.leverx.leverxpetsspringbootproxy.service.AuthorizationHeaderService;

import java.io.IOException;
import java.util.List;

import static ru.leverx.leverxpetsspringbootproxy.client.constants.DestinationConstants.GENERAL_URL;
import static ru.leverx.leverxpetsspringbootproxy.client.constants.DestinationConstants.HTTP_CLIENT;
import static ru.leverx.leverxpetsspringbootproxy.client.constants.DestinationConstants.OBJECT_MAPPER;

@Repository
@RequiredArgsConstructor
@Slf4j
public class HttpDestinationClientPeopleImpl implements HttpDestinationClientPeople {

    private final AuthorizationHeaderService authorizationHeaderService;

    @Override
    public PersonResponseDto httpGetPersonById(Long id, String token) throws IOException {

        HttpUriRequest uriRequest = new HttpGet(String.format("%s/people/%s", GENERAL_URL, id));

        authorizationHeaderService.addAuthorizationHeader(uriRequest, token);
        //uriRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        //uriRequest.addHeader(token, "Authorization");

        HttpResponse executedUrl = HTTP_CLIENT.execute(uriRequest);
        HttpEntity entity = executedUrl.getEntity();

        PersonResponseDto personResponseDto = OBJECT_MAPPER.readValue(EntityUtils.toString(entity), PersonResponseDto.class);
        return personResponseDto;
    }

    @Override
    public List<PersonResponseDto> httpGetPeople(String token) throws IOException {
        HttpUriRequest uriRequest = new HttpGet(String.format("%s/people", GENERAL_URL));

        PrincipalFacade principalFacade = PrincipalAccessor.getPrincipalFacade();

        ((ScpCfPrincipalFacade) principalFacade).setIdExtractorFunction(
                "urn:ietf:params:oauth:grant-type:jwt-bearer",
                jwt -> jwt.getClaim("user_name").asString());
        log.info("LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOK -  " + principalFacade.toString());

        authorizationHeaderService.addAuthorizationHeader(uriRequest, token);
        HttpResponse executedUrl = HTTP_CLIENT.execute(uriRequest);
        HttpEntity entity = executedUrl.getEntity();

        List<PersonResponseDto> personResponseDtos = OBJECT_MAPPER.readValue(
                EntityUtils.toString(entity),
                new TypeReference<List<PersonResponseDto>>() {
                });
        return personResponseDtos;
    }

    @Override
    public void httpDeletePersonById(Long id) throws IOException {
        HttpUriRequest uriRequest = new HttpDelete(String.format("%s/people/%s", GENERAL_URL, id));

        HTTP_CLIENT.execute(uriRequest);
    }

    @Override
    public PersonResponseDto httpCreatePerson(PersonRequestDto personRequestDto) throws IOException {
        HttpPost uriRequest = new HttpPost(String.format("%s/people", GENERAL_URL));


        String body = OBJECT_MAPPER.writeValueAsString(personRequestDto);
        uriRequest.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        uriRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpResponse executedUrl = HTTP_CLIENT.execute(uriRequest);
        HttpEntity entity = executedUrl.getEntity();

        PersonResponseDto personResponseDto = OBJECT_MAPPER.readValue(EntityUtils.toString(entity), PersonResponseDto.class);
        return personResponseDto;
    }

    @Override
    public PersonResponseDto httpUpdatePersonById(Long id, PersonRequestDto personRequestDto) throws IOException {
        HttpPut uriRequest = new HttpPut(String.format("%s/people/%s", GENERAL_URL, id));


        String body = OBJECT_MAPPER.writeValueAsString(personRequestDto);
        uriRequest.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        uriRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpResponse executedUrl = HTTP_CLIENT.execute(uriRequest);
        HttpEntity entity = executedUrl.getEntity();

        PersonResponseDto personResponseDto = OBJECT_MAPPER.readValue(EntityUtils.toString(entity), PersonResponseDto.class);
        return personResponseDto;
    }
}
