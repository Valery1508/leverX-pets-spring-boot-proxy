package ru.leverx.leverxpetsspringbootproxy.service;

import org.apache.http.client.methods.HttpUriRequest;

/**
 * @author Valeryia Zubrytskaya
 */
public interface AuthorizationHeaderService {

    void addAuthorizationHeader(HttpUriRequest uriRequest, String token);

}
