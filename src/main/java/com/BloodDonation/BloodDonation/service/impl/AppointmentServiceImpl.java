package com.BloodDonation.BloodDonation.service.impl;

import com.BloodDonation.BloodDonation.entity.Appointment;
import com.BloodDonation.BloodDonation.entity.users.Donor;
import com.BloodDonation.BloodDonation.repository.AppointmentRepository;
import com.BloodDonation.BloodDonation.service.AppointmentService;
import com.BloodDonation.BloodDonation.service.notification.*;
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

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
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
        createNotificationServices(newAppointment).sendAppointmentConfirmation();
        return newAppointment;
    }

    @Scheduled(cron = "0 37 22 * * *", zone="Europe/Bucharest")
    @Async
    protected void sendAppointmentReminders() {
        System.out.println("appointment reminders");
        Appointment[] appointments = appointmentRepository.findByDate(LocalDate.now().plusDays(1));
        //made using decorator
        for (Appointment appointment : appointments) {
            createNotificationServices(appointment).sendAppointmentReminder();
        }
    }

    private NotificationService createNotificationServices(Appointment appointment){
        Donor donor = appointment.getDonor();
        NotificationService notification = new BasicNotification();
        if (donor.getEmailNotification() == 1) {
            notification = new EmailService(notification, appointment);
        } if (donor.getSmsNotification() == 1) {
            notification = new SmsService(notification, appointment);
        }
        return notification;
    }


}
