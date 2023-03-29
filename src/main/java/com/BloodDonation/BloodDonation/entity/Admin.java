package com.BloodDonation.BloodDonation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "Admin")
@Table(name = "app_admin")
public class Admin extends User{
    public Admin() {
    }

    public Admin(String email, String password, String firstName, String lastName, String userType) {
        super(email, password, firstName, lastName, userType);
    }
}
