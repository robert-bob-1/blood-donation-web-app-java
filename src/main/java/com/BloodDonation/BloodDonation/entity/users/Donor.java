package com.BloodDonation.BloodDonation.entity.users;

import com.BloodDonation.BloodDonation.entity.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.transaction.annotation.Propagation;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Donor")
@Table(name = "donor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Donor extends User {

    @Size(max = 3)
    @NotNull
    public String bloodType;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "user_id")
    private List<Appointment> appointments = new ArrayList<>();

    public Donor(String email, String password, String firstName, String lastName, String userType, String bloodType) {
        super(email, password, firstName, lastName, userType);
        this.bloodType = bloodType;
    }

    public Donor() {

    }

    @Transactional
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
//        appointment.setDonor(this);
    }
    @Transactional
    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
//        appointment.setDonor(null);
    }
}
