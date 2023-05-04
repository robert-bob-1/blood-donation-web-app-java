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
        smsMessage1.body("Test SMS 1");
        smsMessage1.to("+40731727640");
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
}
