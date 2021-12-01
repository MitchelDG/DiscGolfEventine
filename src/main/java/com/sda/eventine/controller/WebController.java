package com.sda.eventine.controller;

import com.sda.eventine.dto.EventDTO;
import com.sda.eventine.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class WebController {

    @GetMapping(
            value = "/api/event"
    )
    public String getIndex(
            final ModelMap modelMap,
            @RequestParam(defaultValue = "unknown") String name
    ) {
        modelMap.addAttribute("name",name);

        return "event";
    }

    @GetMapping(
            value = "/register"
    )
    public String showForm(Model model) {
        User user = new User();
        model.addAttribute("user",user);

        List<String> roleList = Arrays.asList("Developer", "Designer", "Architect");
        model.addAttribute("roleList", roleList);
        return"register_form";
    }

    @PostMapping(
            value = "/register"
    )
    public String submitForm(@ModelAttribute("user") User user){
        System.out.println(user);
        return  "register_success";
    }
//
//    @GetMapping(value = "/login")
//    public String displayLogin() {
//        return "registration";
//    }

    @PostMapping(value = "/api/user/register")
    public String registerUser(@ModelAttribute("user") User user){
        return "index";
    }

    @PostMapping(value = "/api/event/create")
    public String registerEvent(@ModelAttribute("eventDTO") EventDTO eventDTO){
        return "event_form";
    }

}
