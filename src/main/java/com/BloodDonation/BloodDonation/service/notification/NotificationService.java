package com.BloodDonation.BloodDonation.service.notification;

import com.BloodDonation.BloodDonation.entity.Appointment;

public interface NotificationService {
    void sendAppointmentReminder(Appointment appointment);
}
