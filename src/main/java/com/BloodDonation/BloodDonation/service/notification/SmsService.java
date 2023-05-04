package com.BloodDonation.BloodDonation.service.notification;

import com.BloodDonation.BloodDonation.entity.Appointment;
import org.springframework.stereotype.Service;

@Service
public class SmsService implements NotificationService {
    @Override
    public void sendAppointmentReminder(Appointment appointment) {

    }
}
