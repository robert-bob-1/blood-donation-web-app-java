package com.BloodDonation.BloodDonation.repository;

import com.BloodDonation.BloodDonation.entity.users.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    Optional<Doctor> findByEmail(String email);

    Integer deleteByEmail(String email);

}
