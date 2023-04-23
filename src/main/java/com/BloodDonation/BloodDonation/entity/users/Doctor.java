package com.BloodDonation.BloodDonation.entity.users;

import com.BloodDonation.BloodDonation.entity.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Doctor extends User{

    public Doctor() {
    }

    public Doctor(String email, String password, String firstName, String lastName, String userType) {
        super(email, password, firstName, lastName, userType);
    }

}
