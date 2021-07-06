package ru.leverx.leverxpetsspringbootproxy.client;

import com.fasterxml.jackson.core.type.TypeReference;
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
import ru.leverx.leverxpetsspringbootproxy.dto.PetDto;
import ru.leverx.leverxpetsspringbootproxy.dto.SwapPetsDto;

import java.io.IOException;
import java.util.List;

import static ru.leverx.leverxpetsspringbootproxy.client.constants.DestinationConstants.GENERAL_URL;
import static ru.leverx.leverxpetsspringbootproxy.client.constants.DestinationConstants.HTTP_CLIENT;
import static ru.leverx.leverxpetsspringbootproxy.client.constants.DestinationConstants.OBJECT_MAPPER;

@Repository
public class HttpDestinationClientPets {

    public PetDto httpGetPetById(Long id) throws IOException {

        HttpUriRequest uriRequest = new HttpGet(String.format("%s/pets/%s", GENERAL_URL, id));

        HttpResponse execute = HTTP_CLIENT.execute(uriRequest);
        HttpEntity entity = execute.getEntity();

        PetDto petDto = OBJECT_MAPPER.readValue(EntityUtils.toString(entity), PetDto.class);
        return petDto;
    }

    public List<PetDto> httpGetPets() throws IOException {
        HttpUriRequest uriRequest = new HttpGet(String.format("%s/pets", GENERAL_URL));

        HttpResponse execute = HTTP_CLIENT.execute(uriRequest);
        HttpEntity entity = execute.getEntity();

        List<PetDto> petDtos = OBJECT_MAPPER.readValue(
                EntityUtils.toString(entity),
                new TypeReference<List<PetDto>>() {
                });
        return petDtos;
    }

    public void httpDeletePetById(Long id) throws IOException {
        HttpUriRequest uriRequest = new HttpDelete(String.format("%s/pets/%s", GENERAL_URL, id));

        HTTP_CLIENT.execute(uriRequest);
    }

    public PetDto httpCreatePet(PetDto petDto) throws IOException {
        HttpPost uriRequest = new HttpPost(String.format("%s/pets", GENERAL_URL));


        String body = OBJECT_MAPPER.writeValueAsString(petDto);
        uriRequest.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        uriRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpResponse execute = HTTP_CLIENT.execute(uriRequest);
        HttpEntity entity = execute.getEntity();

        PetDto petResponseDto = OBJECT_MAPPER.readValue(EntityUtils.toString(entity), PetDto.class);
        return petResponseDto;
    }

    public PetDto httpUpdatePetById(Long id, PetDto petDto) throws IOException {
        HttpPut uriRequest = new HttpPut(String.format("%s/pets/%s", GENERAL_URL, id));


        String body = OBJECT_MAPPER.writeValueAsString(petDto);
        uriRequest.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        uriRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpResponse execute = HTTP_CLIENT.execute(uriRequest);
        HttpEntity entity = execute.getEntity();

        PetDto petResponseDto = OBJECT_MAPPER.readValue(EntityUtils.toString(entity), PetDto.class);
        return petResponseDto;
    }

    public void httpSwapPets(SwapPetsDto swapPetsDto) throws IOException {
        HttpPost uriRequest = new HttpPost(String.format("%s/pets/swap", GENERAL_URL));


        String body = OBJECT_MAPPER.writeValueAsString(swapPetsDto);
        uriRequest.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        uriRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpResponse execute = HTTP_CLIENT.execute(uriRequest);
        HttpEntity entity = execute.getEntity();
    }
}
