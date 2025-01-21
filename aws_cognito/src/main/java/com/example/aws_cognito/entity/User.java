package com.example.aws_cognito.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    private String username;  // Use username from Cognito as primary key

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = true)
    private String role;  // You can define roles (ADMIN, USER, etc.)

    // Constructors, getters, and setters
    public User() {}

    public User(String username, String email, String fullName, String role) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
    }

    // Getters and setters
}

