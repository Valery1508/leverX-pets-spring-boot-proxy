package ru.leverx.leverxpetsspringbootproxy.service.impl;

import lombok.AllArgsConstructor;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.stereotype.Service;
import ru.leverx.leverxpetsspringbootproxy.service.AuthorizationHeaderService;

/**
 * @author Valeryia Zubrytskaya
 */
@Service
@AllArgsConstructor
public class AuthorizationHeaderServiceImpl implements AuthorizationHeaderService {

    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";

    @Override
    public void addAuthorizationHeader(HttpUriRequest uriRequest, String token) {
        uriRequest.addHeader(AUTHORIZATION_HEADER_NAME, token);
    }
}
