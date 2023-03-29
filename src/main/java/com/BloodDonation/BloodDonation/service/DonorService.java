package com.BloodDonation.BloodDonation.service;

import com.BloodDonation.BloodDonation.dto.UserDTO;
import com.BloodDonation.BloodDonation.entity.Donor;
import com.BloodDonation.BloodDonation.entity.User;

import java.util.UUID;

public interface DonorService {
    Donor registerDonor(Donor donor);

    Donor getDonorByEmail(String email);
}