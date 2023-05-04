package com.BloodDonation.BloodDonation.service.notification;

public class BasicNotification implements NotificationService {

    @Override
    public void sendAppointmentReminder() {
        System.out.println("sending reminders");
    }

    @Override
    public void sendAppointmentConfirmation() {
        System.out.println("sending confirmations");
    }
}
