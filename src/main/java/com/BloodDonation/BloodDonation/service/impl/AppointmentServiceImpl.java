package com.BloodDonation.BloodDonation.service.impl;

import com.BloodDonation.BloodDonation.entity.Appointment;
import com.BloodDonation.BloodDonation.entity.users.Doctor;
import com.BloodDonation.BloodDonation.entity.users.Donor;
import com.BloodDonation.BloodDonation.entity.Location;
import com.BloodDonation.BloodDonation.repository.AppointmentRepository;
import com.BloodDonation.BloodDonation.service.AppointmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment createAppointment(Donor donor, Location location, String date) {
//        Appointment newAppointment = new Appointment(
//                donor.id, location.getId(), date);
//
//        return appointmentRepository.save(newAppointment);
        return null;
    }

    @Override
    public Appointment addDoctorToAppointment(Appointment appointment, Doctor doctor) {
        return null;
    }

    @Override
    public Appointment[] getAppointmentsByDonor(UUID donor) {
        return appointmentRepository.findByDonorId(donor);
    }

    @Override
    public void deleteAppointment(UUID id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByDonor(Donor donor) {
        appointmentRepository.deleteByDonor(donor.id);
    }

    @Override
    public ArrayList<Appointment> getAppointmentsByLocation(Location location) {
        return appointmentRepository.findByLocationId(location.getId());
    }

    @Override
    public void validateAppointment(Appointment selectedAppointment, Doctor doctor) {
        appointmentRepository.findById(selectedAppointment.getId())
                .ifPresent(appointment -> {
                    appointment.setDoctorId(doctor.getId());

                    appointmentRepository.save(appointment);
                });
    }

    @Override
    public Appointment addAppointment(Appointment appointment) {
        Appointment newAppointment = appointmentRepository.save(appointment);
        return newAppointment;
    }
}
