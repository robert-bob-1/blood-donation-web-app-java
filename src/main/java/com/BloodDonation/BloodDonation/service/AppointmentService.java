package com.BloodDonation.BloodDonation.service;

import com.BloodDonation.BloodDonation.entity.users.Doctor;
import com.BloodDonation.BloodDonation.entity.users.Donor;
import com.BloodDonation.BloodDonation.entity.Appointment;

import java.util.UUID;

public interface AppointmentService {
    Appointment[] getAppointmentsByDonor(UUID donor);
    void deleteAppointment(UUID id);

    void deleteByDonor(Donor donor);

    Appointment[] getAppointmentsByLocation(UUID uuid);

    void updateAppointment(Appointment appointment);
    Appointment addAppointment(Appointment appointment);
}
