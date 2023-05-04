package com.BloodDonation.BloodDonation.service.mail;

import com.BloodDonation.BloodDonation.entity.Appointment;
import com.BloodDonation.BloodDonation.entity.users.Donor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendAppointmentConfirmation(Appointment appointment) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("robert.bob.1709@gmail.com");
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
}
