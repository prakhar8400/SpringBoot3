package io.reflectoring.SpringBoot3.services;
import org.springframework.stereotype.Service;

@Service
public class MessageServices {
    public MessageServices() {
        System.out.println("MessageServices Bean Created!");  // Debugging
    }


        public String getGreetingMessage() {

            return "Hello World";
        }

    }

