package io.reflectoring.SpringBoot3.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "greetings")  // Use the correct table name
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    // Constructors
    public Greeting() {}

    public Greeting(String message) {
        this.message = message;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
