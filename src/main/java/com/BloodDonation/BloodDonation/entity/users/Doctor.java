package com.BloodDonation.BloodDonation.entity.users;

import com.BloodDonation.BloodDonation.entity.Appointment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Doctor extends User{

//    @OneToMany(
////            mappedBy = "doctor",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<Appointment> appointments = new ArrayList<>();

    public Doctor() {
    }

    public Doctor(String email, String password, String firstName, String lastName, String userType) {
        super(email, password, firstName, lastName, userType);
    }

//    public void addAppointment(Appointment appointment) {
//        appointments.add(appointment);
////        appointment.setDoctor(this);
//    }
//
//    public void removeAppointment(Appointment appointment) {
//        appointments.remove(appointment);
////        appointment.setDoctor(null);
//    }
}
