package com.sda.eventine.controller;

import com.sda.eventine.dto.EventDTO;
import com.sda.eventine.dto.UserDTO;
import com.sda.eventine.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TemplateController {



    private final EventService eventService;
    private final UserService userService;
    private final RegistrationService registrationService;
    private final ParticipationService participationService;
    private final CustomUserDetailsService detailsService;


    @RequestMapping(value = "login" )
    public String login() {
        return "login";
    }


    @PostMapping(value = "login")
    public String signIn() {
        return "index";
    }


    @GetMapping(value = "registration" )
    public String registration() {
        return "registration";
    }


    @PostMapping(value = "registration")
    public String registerUser(@ModelAttribute(value = "userDTO") UserDTO userDTO) {
//        userService.signUpUser(userDTO);
        return "registration";
    }


    @RequestMapping(value = "")
    public String root(Model model) {
        model.addAttribute("listOfEvents", eventService.findAll());
        return "index";
    }


    @RequestMapping(value = "index")
    public String index(Model model) {
        model.addAttribute("listOfEvents", eventService.findAll());
        return "index";
    }


    @RequestMapping(value = "about" )
    public String about() {
        return "about";
    }


    @GetMapping (value = "application_to_event")
    public String applicationToEvent(Model model) {
//        var thisEvent = eventService.findById(5L);
//        var owner = detailsService.getCurrentUserName();
//        model.addAttribute("thisEvent", thisEvent);
//        model.addAttribute("owner", owner);
//        participationService.connect(thisEvent.getId(), owner.getId());
        return "application_to_event";
    }


    @GetMapping(value = "event_form")
    public String eventForm(Model model) {
        EventDTO eventDTO = new EventDTO();
        model.addAttribute("eventDTO", eventDTO);
        return "event_form";
    }


    @PostMapping(value = "index")
    public String submitEventForm(@ModelAttribute(value = "eventDTO") EventDTO eventDTO) {
        eventService.createEvent(eventDTO);
        return "index";
    }


    @GetMapping(value = "/comment")
    public String comment() {
        return "comment";
    }


}
