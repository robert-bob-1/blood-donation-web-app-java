package com.BloodDonation.BloodDonation.repository;

import com.BloodDonation.BloodDonation.entity.Doctor;
import com.BloodDonation.BloodDonation.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    Optional<Doctor> findByEmail(String email);

    Integer deleteByEmail(String email);

}
