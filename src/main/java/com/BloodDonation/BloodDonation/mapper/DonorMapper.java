package com.BloodDonation.BloodDonation.mapper;

import com.BloodDonation.BloodDonation.dto.DonorCreateDTO;
import com.BloodDonation.BloodDonation.entity.users.Donor;
import org.springframework.stereotype.Component;

@Component
public class DonorMapper {

    public Donor fromCreateDTOToDonor(DonorCreateDTO dto){
        Donor donor = new Donor();
        donor.email = dto.email;
        donor.password = dto.password;
        donor.firstName = dto.firstName;
        donor.lastName = dto.lastName;
        donor.userType = "donor";
        donor.setPhoneNumber(dto.phoneNumber);
        donor.bloodType = dto.bloodType;
        donor.setSmsNotification(dto.smsNotification);
        donor.setEmailNotification(dto.emailNotification);

        return donor;
    }
}
