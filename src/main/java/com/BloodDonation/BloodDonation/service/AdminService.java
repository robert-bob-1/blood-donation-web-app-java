package com.BloodDonation.BloodDonation.service;

import com.BloodDonation.BloodDonation.entity.users.Admin;
import com.BloodDonation.BloodDonation.entity.users.Doctor;

import java.util.List;
import java.util.UUID;

public interface AdminService {
    Doctor registerDoctor(Doctor doctor);

    Integer deleteDoctorByEmail(String email);

    void updateDoctor(String value, Doctor newDoctor);

    Admin getAdmin(UUID userID);

    List<Doctor> getDoctors();

    Admin getAdminByEmail(String email);
}
