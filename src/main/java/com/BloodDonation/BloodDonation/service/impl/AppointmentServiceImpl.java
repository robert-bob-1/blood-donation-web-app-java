package com.BloodDonation.BloodDonation.service.impl;

import com.BloodDonation.BloodDonation.entity.Appointment;
import com.BloodDonation.BloodDonation.entity.users.Donor;
import com.BloodDonation.BloodDonation.repository.AppointmentRepository;
import com.BloodDonation.BloodDonation.service.AppointmentService;
import com.BloodDonation.BloodDonation.service.mail.EmailService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final EmailService emailService;
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, EmailService emailService) {
        this.appointmentRepository = appointmentRepository;
        this.emailService = emailService;
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
    public Appointment[] getAppointmentsByLocation(UUID uuid) {
        return appointmentRepository.findByLocationId(uuid);
    }

    @Override
    public Appointment[] getAppointmentsToday() {
        return appointmentRepository.findByDate(LocalDate.now());
    }

    @Override
    public Page<Appointment> getAppointments(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        appointmentRepository.findById(appointment.getId())
                .ifPresent(oldAppointment -> {
                    oldAppointment.setDoctor(appointment.getDoctor());

                    appointmentRepository.save(appointment);
                });
    }

    @Override
    public Appointment addAppointment(Appointment appointment) {
        Appointment newAppointment = appointmentRepository.save(appointment);
        emailService.sendAppointmentConfirmation(newAppointment);
        return newAppointment;
    }




}
