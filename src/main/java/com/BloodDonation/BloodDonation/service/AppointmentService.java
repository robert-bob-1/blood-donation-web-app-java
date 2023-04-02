package com.BloodDonation.BloodDonation.service;

import com.BloodDonation.BloodDonation.entity.users.Doctor;
import com.BloodDonation.BloodDonation.entity.users.Donor;
import com.BloodDonation.BloodDonation.entity.Appointment;
import com.BloodDonation.BloodDonation.entity.Location;

import java.util.ArrayList;

public interface AppointmentService {
    Appointment createAppointment(Donor donor, Location location, String datetime);
    Appointment addDoctorToAppointment(Appointment appointment, Doctor doctor);

    ArrayList<Appointment> getAppointmentsByDonor(Donor donor);


}
