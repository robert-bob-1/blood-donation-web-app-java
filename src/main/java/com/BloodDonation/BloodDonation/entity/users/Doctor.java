package com.BloodDonation.BloodDonation.entity.users;

import com.BloodDonation.BloodDonation.entity.Appointment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Doctor extends User{

    @OneToMany(
//            mappedBy = "doctor",
            cascade = CascadeType.ALL, // nu aș ține un cascade all pentru că doctorul nu creză/șterge appointments. nu sunt chiar așa de conectate entitățile astea două între ele
            orphanRemoval = true    // daca un doctor e șters din sistem, asta nu înseamnă că toate programările pe care le-a validat vor fi șterse (așa ele ar dispărea și pentru user)
    )
    private List<Appointment> appointments = new ArrayList<>();

    public Doctor() {
    }

    public Doctor(String email, String password, String firstName, String lastName, String userType) {
        super(email, password, firstName, lastName, userType);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
//        appointment.setDoctor(this);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
//        appointment.setDoctor(null);
    }
}
