package ru.lifeincode.pottery_calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class CalendarController {

    @RequestMapping(value="/calendar", method = RequestMethod.GET)
    public String showCalendarPage(ModelMap model){
        return "calendar";
    }


}
