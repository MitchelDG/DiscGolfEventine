package com.sda.eventine.controller;

import com.sda.eventine.dto.CommentDTO;
import com.sda.eventine.dto.EventDTO;
import com.sda.eventine.dto.UserDTO;
import com.sda.eventine.model.Participation;
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
    private final RegistrationService registrationService;
    private final UserService userService;
    private final ParticipationService participationService;
    private final CommentService commentService;


    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }


    @PostMapping(value = "login")
    public String signIn() {
        return "redirect:index";
    }


    @GetMapping(value = "registration")
    public String registration(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute(userDTO);
        return "registration";
    }


    @PostMapping(value = "registration")
    public String registerUser(@ModelAttribute(value = "userDTO") UserDTO userDTO) {
        registrationService.register(userDTO);
        return "redirect:login";
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


    @RequestMapping(value = "about")
    public String about() {
        return "about";
    }


    @GetMapping(value = "application_to_event")
    public String applicationToEvent(Model model) {
        Participation participation = new Participation();
        model.addAttribute("participants", userService.participantNames(1L/* current event ID*/));
        model.addAttribute(participation);
        return "application_to_event";
    }

    @PostMapping(value = "application_to_event")
    public String applyToEvent(Model model) {
        Participation participation = new Participation();
        model.addAttribute(participation);
        participationService.connect(1L/* current event ID*/, 23L /*detailsService.getCurrentUser().getId()*/);
        return "redirect:application_to_event";
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

    //TODO: insert working event ID

    @GetMapping(value = "comment")
    public String comment(Model model) {
        model.addAttribute("listOfComments", commentService.findAllComments(1L/* current event ID*/));
        CommentDTO comment = new CommentDTO();
        String name = "MitchelDG"; /*detailsService.getCurrentUser().getName();*/
        model.addAttribute("comment", comment);
        model.addAttribute("publisher", name);
        return "comment";
    }


    @PostMapping(value = "comment")
    public String postComment(@ModelAttribute(value = "comment") CommentDTO comment) {
        commentService.saveComment(1L/* insert current event id here*/, comment);
        return "redirect:comment";
    }
}
