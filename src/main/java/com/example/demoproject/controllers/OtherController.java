package com.example.demoproject.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/other")
public class OtherController {

    @GetMapping(value="/about")
    public String about(){
        return "about";
    }

}
