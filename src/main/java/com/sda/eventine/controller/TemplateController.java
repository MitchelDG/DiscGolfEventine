package com.sda.eventine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping(value = "login" )
    public String getLoginView() {
        return "registration";
    }

    @GetMapping(value = "index" )
    public String getIndex() {
        return "index";
    }




}
