package com.fastcampus.jpa.bookmanager.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "hello-world";
    }

    @GetMapping
    public String helloWorld2() {
        return "hello-world2";
    }



}
