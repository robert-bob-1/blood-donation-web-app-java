package com.BloodDonation.BloodDonation.service;

import com.BloodDonation.BloodDonation.entity.users.Doctor;
import com.BloodDonation.BloodDonation.entity.users.Donor;
import com.BloodDonation.BloodDonation.entity.Appointment;
import com.BloodDonation.BloodDonation.entity.Location;

import java.util.ArrayList;
import java.util.UUID;

public interface AppointmentService {
    Appointment createAppointment(Donor donor, Location location, String date);
    Appointment addDoctorToAppointment(Appointment appointment, Doctor doctor);

    Appointment[] getAppointmentsByDonor(UUID donor);
    void deleteAppointment(UUID id);

    void deleteByDonor(Donor donor);

    ArrayList<Appointment> getAppointmentsByLocation(Location location);

    void validateAppointment(Appointment selectedAppointment, Doctor doctor);

    Appointment addAppointment(Appointment appointment);
}
