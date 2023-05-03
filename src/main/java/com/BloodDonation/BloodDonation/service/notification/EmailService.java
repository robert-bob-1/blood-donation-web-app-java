package com.BloodDonation.BloodDonation.service.notification;

import com.BloodDonation.BloodDonation.entity.Appointment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements NotificationService{

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Async
    public void sendAppointmentConfirmation(Appointment appointment) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(((JavaMailSenderImpl)emailSender).getUsername());
        message.setTo(appointment.getDonor().getEmail());
        message.setSubject("Appointment confirmation");
        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append("Dear " + appointment.getDonor().firstName+"\n");
        textBuilder.append("\tYour appointment has been succesfully created with the following details:\n");
        textBuilder.append("Date: " + appointment.getDate().toString() + "\n");
        textBuilder.append("Time: " + appointment.getTime().toString() + "\n");
        textBuilder.append("Location: " + appointment.getLocation().getName() + "\n");
        message.setText(textBuilder.toString());

        emailSender.send(message);
    }

    @Override
    public void sendAppointmentReminder(Appointment appointment) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(((JavaMailSenderImpl)emailSender).getUsername());
        message.setTo(appointment.getDonor().getEmail());
        message.setSubject("Appointment reminder");
        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append("Dear " + appointment.getDonor().firstName+"\n");
        textBuilder.append("\tWe are writing to remind you of your appointment for tomorrow:\n");
        textBuilder.append("Date: " + appointment.getDate().toString() + "\n");
        textBuilder.append("Time: " + appointment.getTime().toString() + "\n");
        textBuilder.append("Location: " + appointment.getLocation().getName() + "\n");
        message.setText(textBuilder.toString());

        emailSender.send(message);
    }
}
