package io.reflectoring.SpringBoot3.controllers;

import io.reflectoring.SpringBoot3.services.MessageServices;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {
    // UC1
    @GetMapping(path = "/getgreetings")
    public String getGreeting() {
        return "{\"message\": \"Hello, this is a GET request!\"}";
    }

    @PostMapping(path = "postgreetings")
    public String postGreeting(@RequestBody String name) {
        return "{\"message\": \"Hello, this is a POST request!\", \"received\": \"" + name + "\"}";
    }

    @PutMapping(path = "putgreetings")
    public String putGreeting(@RequestBody String name) {
        return "{\"message\": \"Hello, this is a PUT request!\", \"updated\": \"" + name + "\"}";
    }

    @DeleteMapping(path = "deletegreetings")
    public String deleteGreeting() {
        return "{\"message\": \"Hello, this is a DELETE request!\"}";
    }

}