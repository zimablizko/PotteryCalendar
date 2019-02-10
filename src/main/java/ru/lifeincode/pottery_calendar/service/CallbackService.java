
package ru.lifeincode.pottery_calendar.service;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

@Service
public class CallbackService {

    public void autorization(String authCode) throws IOException {

        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        //Create the authorization code flow manager
        Set<String> scope = Collections.singleton(CalendarScopes.CALENDAR);
        String clientId = "308096962905-b0kl29hu11nqi10i2nh83ua3ksdb1jp0.apps.googleusercontent.com";
        String clientSecret = "4G3pQRbun7UhL0ldRqcM1X2Q";

        //Use a factory pattern to create the code flow
        AuthorizationCodeFlow.Builder codeFlowBuilder =
                new GoogleAuthorizationCodeFlow.Builder(
                        httpTransport,
                        jsonFactory,
                        clientId,
                        clientSecret,
                        scope
                );
        AuthorizationCodeFlow codeFlow = codeFlowBuilder.build();

        //set the code flow to use a dummy user
        //in a servlet, this could be the session id
        String userId = "ipeech";
//4/3wAsv7nlH3hqNnDL7Tq6YzKrHA6PM-bWH31X44XcHQsx-7epEGDXb8HUrKVlphd6dfR58LqP2xDqVz6fgEtLQJU
        //"redirect" to the authentication url
        String redirectUri = "http://www.generatorep.ru:8080/callback";
        AuthorizationCodeRequestUrl authorizationUrl = codeFlow.newAuthorizationUrl();
        authorizationUrl.setRedirectUri(redirectUri);
        System.out.println("Go to the following address:");
        System.out.println(authorizationUrl);

        //use the code that is returned as a url parameter
        //to request an authorization token
        System.out.println("What is the 'code' url parameter?");
        String code = new Scanner(System.in).nextLine();

        AuthorizationCodeTokenRequest tokenRequest = codeFlow.newTokenRequest(code);
        tokenRequest.setRedirectUri(redirectUri);
        TokenResponse tokenResponse = tokenRequest.execute();

        //Now, with the token and user id, we have credentials
        com.google.api.client.auth.oauth2.Credential credential = codeFlow.createAndStoreCredential(tokenResponse, userId);

        //Credentials may be used to initialize http requests
        HttpRequestInitializer initializer = credential;
        //and thus are used to initialize the calendar service
        Calendar.Builder serviceBuilder = new Calendar.Builder(
                httpTransport, jsonFactory, initializer);
        serviceBuilder.setApplicationName("Example");
        Calendar calendar = serviceBuilder.build();

}
}
