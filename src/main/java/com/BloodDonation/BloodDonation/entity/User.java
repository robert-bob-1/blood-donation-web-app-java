package com.BloodDonation.BloodDonation.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public
    UUID uuid;
    public String email;
    public String password;
    public String firstName;
    public String lastName;

    public User() {}

}
