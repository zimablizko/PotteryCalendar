package ru.lifeincode.pottery_calendar.service;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RequestService {

    public String getCurrentDate() throws ParseException {

        Date date = new Date();
        System.out.println(date.toString());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String current_date= dateFormat.format(date);
        System.out.println(current_date);
        return current_date;
    }
}
