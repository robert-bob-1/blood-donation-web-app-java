package com.BloodDonation.BloodDonation.service.notification;

import ClickSend.Api.SmsApi;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import com.BloodDonation.BloodDonation.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SmsService implements NotificationService {

    @Autowired
    private ApiClient clickSendConfig;

    @Override
    public void sendAppointmentReminder(Appointment appointment) {

        SmsApi apiInstance = new SmsApi(clickSendConfig);
        SmsMessage smsMessage1 = new SmsMessage();
        smsMessage1.body(buildReminderMessage(appointment));
        smsMessage1.to(appointment.getDonor().getPhoneNumber());
        smsMessage1.source("Java");
        List<SmsMessage> smsMessageList = Arrays.asList(smsMessage1);
        SmsMessageCollection smsMessages = new SmsMessageCollection();
        smsMessages.messages(smsMessageList);
        try {
            String result = apiInstance.smsSendPost(smsMessages);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SmsApi#smsSendPost");
            e.printStackTrace();
        }
    }

    private String buildReminderMessage(Appointment appointment) {
        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append("Dear " + appointment.getDonor().firstName+"\n");
        textBuilder.append("\tYou have an appointment due tommorrow:\n");
        textBuilder.append("Date: " + appointment.getDate().toString() + "\n");
        textBuilder.append("Time: " + appointment.getTime().toString() + "\n");
        textBuilder.append("Location: " + appointment.getLocation().getName() + "\n");
        return textBuilder.toString();
    }
}
