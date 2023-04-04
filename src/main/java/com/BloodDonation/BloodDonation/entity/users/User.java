package com.BloodDonation.BloodDonation.entity.users;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "app_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID uuid;
    public String email;
    public String password;
    public String firstName;
    public String lastName;
//    // vezi așa
//    @Enumerated(EnumType.STRING)
//    public UserType userType;
    public String userType; // dacă tot ai enum.... ce ar fi să îl folosești aici? :D


    public User() {}

    public User(String email, String password, String firstName, String lastName, String userType) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserType() {
        return userType;
    }
}
