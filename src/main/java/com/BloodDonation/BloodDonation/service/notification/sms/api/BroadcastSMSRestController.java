package com.BloodDonation.BloodDonation.service.notification.sms.api;

import ClickSend.Api.SmsApi;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import com.BloodDonation.BloodDonation.service.notification.sms.model.MessageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BroadcastSMSRestController {
    @Autowired
    private ApiClient clickSendConfig;

    @PostMapping("/api/sms")
    public ResponseEntity<String> sendSMS(@RequestBody MessageDetails messageDetails){


        SmsApi smsApi = new SmsApi(clickSendConfig);

        SmsMessage smsMessage = new SmsMessage();
        smsMessage.body(messageDetails.getMessageBody());
        smsMessage.to(messageDetails.getPhoneNumber());
        smsMessage.source(messageDetails.getSendingSource());

        List<SmsMessage> smsMessageList= List.of(smsMessage);

// SmsMessageCollection | SmsMessageCollection model
        SmsMessageCollection smsMessages = new SmsMessageCollection();
        smsMessages.messages(smsMessageList);
        try {
            String result = smsApi.smsSendPost(smsMessages);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Exception when calling SmsApi#smsSendPost", HttpStatus.BAD_REQUEST);
    }
}