package com.BloodDonation.BloodDonation.service.impl;

import com.BloodDonation.BloodDonation.entity.Donor;
import com.BloodDonation.BloodDonation.repository.DonorRepository;
import com.BloodDonation.BloodDonation.service.DonorService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DonorServiceImpl implements DonorService {
    private final DonorRepository donorRepository;
//    private final UserRepository userRepository;

    public DonorServiceImpl(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    @Override
    public Donor registerDonor(Donor donor) {
        Donor savedDonor = donorRepository.save(donor);
        return savedDonor;
    }

    @Override
    public Donor getDonorByEmail(String email) {
        Optional<Donor> foundDonor = donorRepository.findByEmail(email);
        return foundDonor.orElse(null);
    }
}
