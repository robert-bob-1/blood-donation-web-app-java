package com.BloodDonation.BloodDonation.service;

import com.BloodDonation.BloodDonation.entity.users.Donor;
import com.BloodDonation.BloodDonation.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AppointmentService {
    Appointment[] getAppointmentsByDonor(UUID donor);
    void deleteAppointment(UUID id);

    void deleteByDonor(Donor donor);

    Appointment[] getAppointmentsByLocation(UUID uuid);

    Appointment[] getAppointmentsToday();

    Page<Appointment> getAppointments(Pageable pageable);

    void updateAppointment(Appointment appointment);
    Appointment addAppointment(Appointment appointment);

}
