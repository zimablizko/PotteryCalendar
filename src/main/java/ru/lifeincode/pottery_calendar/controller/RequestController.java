package ru.lifeincode.pottery_calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.lifeincode.pottery_calendar.service.LoginService;
import ru.lifeincode.pottery_calendar.service.RequestService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ru.lifeincode.pottery_calendar.service.CalendarService.addEvent;

@Controller
@SessionAttributes("name")
public class RequestController {

    @Autowired
    RequestService service;

    @RequestMapping(value="/add-request-form", method = RequestMethod.POST)
    public String showAddPage(ModelMap model) throws ParseException {

        model.put("current_date", service.getCurrentDate());
        return "add-request";
    }

    @RequestMapping(value="/add-request", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String date, @RequestParam String time, @RequestParam String comment, @RequestParam String type){

        System.out.println("RESPONCE");
        System.out.println(name);
        System.out.println(date);
        System.out.println(time);
        System.out.println(comment);
        System.out.println(type);
        try {
            addEvent(name,date,time,comment,type);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        return "calendar";
    }
}
