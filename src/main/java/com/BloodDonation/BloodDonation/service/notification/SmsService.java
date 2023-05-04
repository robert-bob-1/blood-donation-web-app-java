package com.BloodDonation.BloodDonation.service.notification;

import ClickSend.Api.SmsApi;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import com.BloodDonation.BloodDonation.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Component
public class SmsService extends NotificationDecorator {

//    @Autowired
    private ApiClient clickSendConfig;
    @Autowired
    public void setClickSendConfig(ApiClient apiClient) {
        this.clickSendConfig = apiClient;
    }
    private Appointment appointment;
    public SmsService (NotificationService service, Appointment appointment) {
        super(service);
        this.appointment = appointment;
    }

    @Override
    public void sendAppointmentConfirmation() {
        System.out.println("sending appointment confirmation sms");
    }

    @Override
    public void sendAppointmentReminder() {
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
