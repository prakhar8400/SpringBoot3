package io.reflectoring.SpringBoot3.services;

import org.springframework.stereotype.Service;

@Service
public class MessagingService {

    public void sendMessage(String message) {
        System.out.println("Notification Sent: " + message);

    }
}

