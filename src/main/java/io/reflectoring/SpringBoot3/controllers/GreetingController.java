package io.reflectoring.SpringBoot3.controllers;

import io.reflectoring.SpringBoot3.Entity.Greeting;
import io.reflectoring.SpringBoot3.Repository.GreetingRepository;
import io.reflectoring.SpringBoot3.services.MessageServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GreetingController {
    private final GreetingRepository greetingRepository;
    private final MessageServices obj;

    public GreetingController(GreetingRepository greetingRepository, MessageServices obj) {
        this.greetingRepository = greetingRepository;
        this.obj = obj;
    }

    // UC1: Handling different HTTP methods
    @GetMapping(path = "/getgreetings")
    public String getGreeting() {
        return "{\"message\": \"Hello, this is a GET request!\"}";
    }

    @PostMapping(path = "/postgreetings")
    public String postGreeting(@RequestBody String name) {
        return "{\"message\": \"Hello, this is a POST request!\", \"received\": \"" + name + "\"}";
    }

    @PutMapping(path = "/putgreetings")
    public String putGreeting(@RequestBody String name) {
        return "{\"message\": \"Hello, this is a PUT request!\", \"updated\": \"" + name + "\"}";
    }

    @DeleteMapping(path = "/deletegreetings")
    public String deleteGreeting() {
        return "{\"message\": \"Hello, this is a DELETE request!\"}";
    }

    // UC2: Using Service Layer
    @GetMapping(path = "/hellogreetings")
    public String getServiceGreeting() {
        return obj.getGreetingMessage();
    }

    // UC3: Personalized Greeting with Query Parameters
    @GetMapping(path = "/query")
    public String getGreeting(@RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName) {
        String message;

        if ((firstName == null || firstName.isEmpty()) && (lastName == null || lastName.isEmpty())) {
            message = "Hello World!";
        } else if (firstName == null || firstName.isEmpty()) {
            message = "Hello Mr./Ms. " + lastName + "!";
        } else if (lastName == null || lastName.isEmpty()) {
            message = "Hello " + firstName + "!";
        } else {
            message = "Hello " + firstName + " " + lastName + "!";
        }
        //UC4
        // Save the greeting to the database
        greetingRepository.save(new Greeting(message));

        return message;
    }

    // Retrieve all saved greetings
    @GetMapping("/all")
    public List getAllGreetings() {
        return greetingRepository.findAll();
    }
}
