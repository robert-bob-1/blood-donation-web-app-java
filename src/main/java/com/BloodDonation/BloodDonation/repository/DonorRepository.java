package com.BloodDonation.BloodDonation.repository;

import com.BloodDonation.BloodDonation.entity.Donor;
import com.BloodDonation.BloodDonation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DonorRepository extends JpaRepository<Donor, UUID>{

    Optional<Donor> findByEmail(String email);

}