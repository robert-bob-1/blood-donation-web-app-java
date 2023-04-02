package com.BloodDonation.BloodDonation.entity.users;

import jakarta.persistence.Entity;

@Entity
public class Doctor extends User{
    public Doctor() {
    }

    public Doctor(String email, String password, String firstName, String lastName, String userType) {
        super(email, password, firstName, lastName, userType);
    }
}
