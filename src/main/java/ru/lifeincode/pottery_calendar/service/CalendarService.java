package ru.lifeincode.pottery_calendar.service;

import com.google.api.client.auth.oauth2.*;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import java.nio.charset.Charset;
import java.util.Collections;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.common.io.Files;

import static com.google.common.io.Resources.getResource;


@Service
public class CalendarService {
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/client_secret.json";

    /**
     * Creates an authorized Credential object.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = CalendarService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
       // InetAddress IP=InetAddress.getLocalHost();
        //System.out.println("IP ADDRESS: "+IP.getHostAddress());
        //LocalServerReceiver receiver = new LocalServerReceiver.Builder().setHost("77.222.63.13").setPort(8888).build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        //LocalServerReceiver receiver = new LocalServerReceiver();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

   // public static void main(String... args) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
/*        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }*/
        //addEvent();
    //    System.out.println("ssss");
  //  }


    /**
     * Be sure to specify the name of your application. If the application name is {@code null} or
     * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
     */

    /** E-mail address of the service account. */
    private static final String SERVICE_ACCOUNT_EMAIL = "servicecalendaracc@cellular-fold-229620.iam.gserviceaccount.com";

    /** Global instance of the HTTP transport. */
    private static HttpTransport httpTransport;



    public static Calendar configureCalendar() {
        try {
            try {
                httpTransport = new NetHttpTransport();
                // check for valid setup
                if (SERVICE_ACCOUNT_EMAIL.startsWith("Enter ")) {
                    System.err.println(SERVICE_ACCOUNT_EMAIL);
                    System.exit(1);
                }
              //  URL loc = CalendarService.class.getResource("/ServiceApp-13c8dce63281.p12");
                InputStream in = CalendarService.class.getResourceAsStream("/cellular-fold-229620-ca2a0d3760d7.p12");
               // InputStream in = CalendarService.class.getResourceAsStream("/cellular-fold-229620-697cb7ce9a24.json");
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
               // String path = loc.getPath();
             //   File file = new File("F:\\Projects\\pottery_calendar\\target\\pottery_calendar-0.0.1-SNAPSHOT.jar!\\BOOT-INF\\classes!\\ServiceApp-13c8dce63281.p12");
             /*   String p12Content = reader.readLine();
                if (p12Content.startsWith("Please")) {
                    System.err.println(p12Content);
                    System.exit(1);
                }
                System.out.println(reader.readLine());*/
                // service account credential (uncomment setServiceAccountUser for domain-wide delegation)
               // GoogleCredential credential = GoogleCredential.fromStream(in);
                //ServiceAccountCredentials credential = ServiceAccountCredentials.fromStream(in);

                GoogleCredential credential = new GoogleCredential.Builder()
                        .setTransport(httpTransport)
                        .setJsonFactory(JSON_FACTORY)
                        .setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
                        .setServiceAccountScopes(Collections.singleton(CalendarScopes.CALENDAR))
                        .setServiceAccountPrivateKeyFromP12File(in)

                        .build();
                Calendar   client = new com.google.api.services.calendar.Calendar.Builder(
                        httpTransport, JSON_FACTORY, credential)
                        .setApplicationName(APPLICATION_NAME).build();
                System.out.println("Client : "+client);
                return client;

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        System.exit(1);
        return null;
    }




    public static void addEvent(String name, String date, String time, String comment, String type) throws IOException, GeneralSecurityException {

        // Build a new authorized API client service.
      /*  final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();*/
   /*
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
        String userId = "USERRRR";
//4/3wAsv7nlH3hqNnDL7Tq6YzKrHA6PM-bWH31X44XcHQsx-7epEGDXb8HUrKVlphd6dfR58LqP2xDqVz6fgEtLQJU
        //"redirect" to the authentication url
        String redirectUri = "http://www.generatorep.ru:8080/login";
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
        Calendar service = serviceBuilder.build();
        */

        Calendar service = configureCalendar();


        DateTime now = new DateTime(System.currentTimeMillis()-60*60*26*1000);
        Events events = service.events().list("primary")
                .setCalendarId("ceramics.petersburg@gmail.com")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }


        String typeName = "";
        if (type=="1")
            typeName="Утиль";
        else
            typeName="Политой";
        Event event = new Event ();
        event.setSummary(name+", "+typeName);
        if (!comment.isEmpty())
            event.setDescription(comment);
        //event.setColorId("blue");//не работает
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date parsedDate = dateFormat.parse(date+" "+time);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            DateTime dts = new DateTime(timestamp);
            event.setStart(new EventDateTime().setDateTime(dts));
            System.out.println(timestamp);
            timestamp.setTime(timestamp.getTime() + 60*60*26*1000);
            System.out.println(timestamp);
            DateTime dte = new DateTime(timestamp);
            event.setEnd(new EventDateTime().setDateTime(dte));
        } catch(Exception e) {

        }

      //  DateTime dts = new DateTime("2019-01-29T09:00:00-07:00");


        try {
            service.events().insert("ceramics.petersburg@gmail.com",event ).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}