package com.BloodDonation.BloodDonation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "Donor")
//@Table(name = "donor")
public class Donor extends User {

    @Size(max = 3)
    @NotNull
    public String bloodType;

    public Donor(String email, String password, String firstName, String lastName, String userType, String bloodType) {
        super(email, password, firstName, lastName, userType);
        this.bloodType = bloodType;
    }

    public Donor() {

    }
}
