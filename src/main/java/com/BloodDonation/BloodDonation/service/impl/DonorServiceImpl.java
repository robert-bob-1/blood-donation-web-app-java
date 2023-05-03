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
        Donor existingDonor = donorRepository.findByEmail(donor.email).get();
        Donor savedDonor = null;
        if(existingDonor == null)
            savedDonor = donorRepository.save(donor);
        return savedDonor;
    }

    @Override
    public Donor getDonorByEmail(String email) {
        Optional<Donor> foundDonor = donorRepository.findByEmail(email);
        return foundDonor.orElse(null);
    }

    @Override
    public Donor updateDonor(Donor newDonor) {
        donorRepository
                .findById(newDonor.id)
                .ifPresent(donor -> {
                    donor.email = newDonor.email;
                    donor.password = newDonor.password;
                    donor.firstName = newDonor.firstName;
                    donor.lastName = newDonor.lastName;
                    donor.setPhoneNumber(newDonor.getPhoneNumber());
                    donor.setSmsNotification(newDonor.getSmsNotification());
                    donor.setEmailNotification(newDonor.getEmailNotification());

                    donorRepository.save(donor);
                });
        return newDonor;
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

    @Override
    public void deleteDonorByEmail(String email) {
        donorRepository.deleteByEmail(email);
    }
}
