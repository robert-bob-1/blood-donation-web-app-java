package com.BloodDonation.BloodDonation.service;

import com.BloodDonation.BloodDonation.entity.Admin;
import com.BloodDonation.BloodDonation.entity.Doctor;

import java.util.List;
import java.util.UUID;

public interface AdminService {
    Doctor registerDoctor(Doctor doctor);

    Integer deleteDoctorByEmail(String email);

    void updateDoctor(String value, Doctor newDoctor);

    Admin getAdmin(UUID userID);

    List<Doctor> getDoctors();
}
