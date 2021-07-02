package ru.leverx.leverxpetsspringbootproxy.client.constants;

import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import org.apache.http.client.HttpClient;

import static com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor.getDestination;
import static com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor.getHttpClient;

public final class DestinationConstants {

    public static String DESTINATION_SERVICE_NAME = "destination-service-subacc";

    public static final HttpDestination DESTINATION = getDestination(DESTINATION_SERVICE_NAME).asHttp();

    public static final HttpClient HTTP_CLIENT = getHttpClient(DESTINATION);

    public static final String GENERAL_URL = DESTINATION.getUri() + "";

}
