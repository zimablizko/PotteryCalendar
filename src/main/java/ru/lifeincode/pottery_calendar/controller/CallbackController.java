package ru.lifeincode.pottery_calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.lifeincode.pottery_calendar.service.CallbackService;
import ru.lifeincode.pottery_calendar.service.RequestService;

import java.io.IOException;


@Controller
@SessionAttributes("name")
public class CallbackController {

    @Autowired
    CallbackService service;


    @RequestMapping(value="/callback", method = RequestMethod.GET)
    public String autorization(ModelMap model,  @RequestParam String code) throws IOException {

        service.autorization(code);

        return "calendar";
    }


}
