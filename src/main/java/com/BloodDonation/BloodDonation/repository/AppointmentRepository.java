package com.BloodDonation.BloodDonation.repository;

import com.BloodDonation.BloodDonation.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    ArrayList<Appointment> findByUserId(UUID uuid);

    void deleteByUserId(UUID uuid);

    ArrayList<Appointment> findByLocationId(UUID id);
}
