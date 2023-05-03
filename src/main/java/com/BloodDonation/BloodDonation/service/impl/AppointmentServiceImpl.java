package com.BloodDonation.BloodDonation.service.impl;

import com.BloodDonation.BloodDonation.entity.Appointment;
import com.BloodDonation.BloodDonation.entity.users.Donor;
import com.BloodDonation.BloodDonation.repository.AppointmentRepository;
import com.BloodDonation.BloodDonation.service.AppointmentService;
import com.BloodDonation.BloodDonation.service.notification.EmailService;
import com.BloodDonation.BloodDonation.service.notification.SmsService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final EmailService emailService;
    private final SmsService smsService;
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, EmailService emailService, SmsService smsService) {
        this.appointmentRepository = appointmentRepository;
        this.emailService = emailService;
        this.smsService = smsService;
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

    @Scheduled(cron = "0 53 21 * * *", zone="Europe/Bucharest")
    @Async
    protected void sendAppointmentReminders() {
        System.out.println("appointment reminders");
        Appointment[] appointments = appointmentRepository.findByDate(LocalDate.now().plusDays(1));

        for (Appointment appointment : appointments) {
            Donor donor = appointment.getDonor();
            if (donor.getEmailNotification() == 1) {
                emailService.sendAppointmentReminder(appointment);
            } if (donor.getSmsNotification() == 1) {
                smsService.sendAppointmentReminder(appointment);
            }
        }
    }

}
