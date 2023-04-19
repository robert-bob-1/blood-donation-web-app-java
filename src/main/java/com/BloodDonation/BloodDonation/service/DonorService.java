package com.BloodDonation.BloodDonation.service;

import com.BloodDonation.BloodDonation.entity.users.Donor;

import java.util.UUID;

public interface DonorService {
    Donor registerDonor(Donor donor);

    Donor getDonorByEmail(String email);

    Donor updateDonor(Donor newDonor);

    Donor getDonor(UUID uuid);

    void deleteDonor(UUID uuid);

    void deleteDonorByEmail(String email);
}