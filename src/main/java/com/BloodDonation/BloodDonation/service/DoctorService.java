package com.BloodDonation.BloodDonation.service;

import com.BloodDonation.BloodDonation.entity.users.Doctor;

import java.util.List;
import java.util.UUID;

public interface DoctorService {
    Doctor registerDoctor(Doctor doctor);

    Doctor getDoctorByEmail(String email);

    Doctor deleteDoctorByEmail(String email);

    Doctor updateDoctor(Doctor newDoctor);

    Doctor getDoctorById(UUID uuid);

    List<Doctor> getDoctors();

    void deleteDoctor(UUID uuid);
}
