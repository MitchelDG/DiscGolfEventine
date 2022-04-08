package com.sda.eventine.controller;

import com.sda.eventine.dto.CommentDTO;
import com.sda.eventine.dto.EventCreateDto;
import com.sda.eventine.dto.UserDTO;
import com.sda.eventine.model.Participation;
import com.sda.eventine.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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


    @GetMapping(value = "login")
    public String login() {
        return "login";
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
    public String root(Model model, Pageable pageable) {
        model.addAttribute("listOfEvents", eventService.findAll(pageable));
        return "index";
    }


    @RequestMapping(value = "index")
    public String index(Model model, Pageable pageable) {
        model.addAttribute("listOfEvents", eventService.findAll(pageable));
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
        EventCreateDto eventCreateDto = new EventCreateDto();
        model.addAttribute("eventDTO", eventCreateDto);
        return "event_form";
    }


    @PostMapping(value = "index")
    public String submitEventForm(@ModelAttribute(value = "eventDTO") EventCreateDto eventCreateDto) {
        eventService.createEvent(eventCreateDto);
        return "redirect:index";
    }

    //TODO: insert working event ID

    @GetMapping(value = "comment")
    public String comment(Model model) {
        model.addAttribute("listOfComments", commentService.findAllComments(1L/* current event ID*/));
        CommentDTO comment = new CommentDTO();
        String name = "MitchelDG"; /*detailsService.getCurrentUser().getName();*/
        model.addAttribute("comment", comment);
                return "comment";
    }


    @PostMapping(value = "comment")
    public String postComment(@ModelAttribute(value = "comment") CommentDTO comment) {
        commentService.saveComment(1L/* insert current event id here*/, comment);
        return "redirect:comment";
    }
}
