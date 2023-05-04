package com.BloodDonation.BloodDonation.service.notification;

import com.BloodDonation.BloodDonation.entity.Appointment;

public class NotificationDecorator implements NotificationService {
    private NotificationService service;

    public NotificationDecorator (NotificationService service) {
        this.service = service;
    }

    @Override
    public void sendAppointmentReminder() {
        this.service.sendAppointmentReminder();
    }

    @Override
    public void sendAppointmentConfirmation() {
        this.service.sendAppointmentConfirmation();
    }
}
