package com.BloodDonation.BloodDonation.service;

import com.BloodDonation.BloodDonation.entity.Doctor;

public interface DoctorService {
    Doctor registerDoctor(Doctor doctor);

    Doctor getDoctorByEmail(String email);

    Doctor deleteDoctorByEmail(String email);

    Doctor updateDoctor(Doctor newDoctor);

}
