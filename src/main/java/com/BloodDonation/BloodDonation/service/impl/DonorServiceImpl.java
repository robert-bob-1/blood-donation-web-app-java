package com.BloodDonation.BloodDonation.service.impl;

import com.BloodDonation.BloodDonation.entity.users.Donor;
import com.BloodDonation.BloodDonation.repository.DonorRepository;
import com.BloodDonation.BloodDonation.service.DonorService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DonorServiceImpl implements DonorService {
    private final DonorRepository donorRepository;

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

    @Override
    public void updateDonor(UUID uuid, Donor newDonor) {
        donorRepository
                .findById(uuid)
                .ifPresent(donor -> {
                    donor.email = newDonor.email;
                    donor.password = newDonor.password;
                    donor.firstName = newDonor.firstName;
                    donor.lastName = newDonor.lastName;

                    donorRepository.save(donor);
                });
    }

    @Override
    public Donor getDonor(UUID uuid) {
        Optional<Donor> donor = donorRepository.findById(uuid);

        return donor.get();
    }

    @Override
    public void deleteDonor(UUID uuid) {
        donorRepository.deleteById(uuid);
    }
}
