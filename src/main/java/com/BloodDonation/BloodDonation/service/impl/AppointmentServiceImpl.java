package com.BloodDonation.BloodDonation.service.impl;

import com.BloodDonation.BloodDonation.entity.Appointment;
import com.BloodDonation.BloodDonation.entity.users.Doctor;
import com.BloodDonation.BloodDonation.entity.users.Donor;
import com.BloodDonation.BloodDonation.entity.Location;
import com.BloodDonation.BloodDonation.repository.AppointmentRepository;
import com.BloodDonation.BloodDonation.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment createAppointment(Donor donor, Location location, String datetime) {
        Appointment newAppointment = new Appointment(
                donor.uuid, location.getId(), datetime);

        return appointmentRepository.save(newAppointment);
    }

    @Override
    public Appointment addDoctorToAppointment(Appointment appointment, Doctor doctor) {
        return null;
    }

    @Override
    public ArrayList<Appointment> getAppointmentsByDonor(Donor donor) {
        return appointmentRepository.findByUserId(donor.uuid);
    }

    @Override
    public Appointment deleteAppointment(Appointment appointment) {
        appointmentRepository.deleteById(appointment.getId());
        return appointment;
    }
}
