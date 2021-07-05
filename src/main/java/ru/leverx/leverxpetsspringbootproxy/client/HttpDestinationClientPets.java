package ru.leverx.leverxpetsspringbootproxy.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Repository;
import ru.leverx.leverxpetsspringbootproxy.dto.PetDto;

import java.io.IOException;
import java.util.List;

import static ru.leverx.leverxpetsspringbootproxy.client.constants.DestinationConstants.GENERAL_URL;
import static ru.leverx.leverxpetsspringbootproxy.client.constants.DestinationConstants.HTTP_CLIENT;

@Repository
public class HttpDestinationClientPets {

    public PetDto httpGetPetById(Long id) throws IOException {

        HttpUriRequest uriRequest = new HttpGet(String.format("%s/pets/%s", GENERAL_URL, id));

        HttpResponse execute = HTTP_CLIENT.execute(uriRequest);
        HttpEntity entity = execute.getEntity();

        ObjectMapper objectMapper = new ObjectMapper();
        PetDto petDto = objectMapper.readValue(EntityUtils.toString(entity), PetDto.class);
        return petDto;
    }

    public List<PetDto> httpGetPets() throws IOException {
        HttpUriRequest uriRequest = new HttpGet(String.format("%s/pets", GENERAL_URL));

        HttpResponse execute = HTTP_CLIENT.execute(uriRequest);
        HttpEntity entity = execute.getEntity();

        ObjectMapper objectMapper = new ObjectMapper();
        List<PetDto> petDtos = objectMapper.readValue(
                EntityUtils.toString(entity),
                new TypeReference<List<PetDto>>() {
                });
        return petDtos;
    }

    public void httpDeletePetById(Long id) throws IOException {
        HttpUriRequest uriRequest = new HttpDelete(String.format("%s/pets/%s", GENERAL_URL, id));

        HTTP_CLIENT.execute(uriRequest);
    }
}
