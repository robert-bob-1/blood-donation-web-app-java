package com.BloodDonation.BloodDonation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync(proxyTargetClass=true)
public class BloodDonationApplication {
	@Value("${clickSend-username}")
	private String clickSendUsername;
	@Value("${clickSend-apiKey}")
	private String clickSendApiKey;
	public static void main(String[] args) {
		SpringApplication.run(BloodDonationApplication.class, args);
	}

}
