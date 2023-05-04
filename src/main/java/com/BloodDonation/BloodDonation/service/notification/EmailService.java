package com.BloodDonation.BloodDonation.service.notification;

import com.BloodDonation.BloodDonation.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EmailService extends NotificationDecorator{

    private JavaMailSender emailSender;
    @Autowired
    public void setEmailSender(JavaMailSender javaMailSender) {
        this.emailSender = emailSender;
    }
    private Appointment appointment;
    public EmailService (NotificationService service, Appointment appointment) {
        super(service);
        this.appointment = appointment;
    }

    @Async
    @Override
    public void sendAppointmentConfirmation() {
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
    public void sendAppointmentReminder() {
        System.out.println("sending email reminder");
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
