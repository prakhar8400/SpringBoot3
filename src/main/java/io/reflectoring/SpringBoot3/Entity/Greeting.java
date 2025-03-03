package io.reflectoring.SpringBoot3.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "greetings")
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String message;

    // Default Constructor
    public Greeting() {}

    // Constructor with Name & Message
    public Greeting(String name, String message) {
        this.name = name;
        this.message = message;
    }

    // Constructor with Message Only
    public Greeting(String message) {
        this.message = message;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
