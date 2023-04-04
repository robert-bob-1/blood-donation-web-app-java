package com.BloodDonation.BloodDonation.service.impl;

import com.BloodDonation.BloodDonation.entity.users.Admin;
import com.BloodDonation.BloodDonation.entity.users.Doctor;
import com.BloodDonation.BloodDonation.repository.AdminRepository;
import com.BloodDonation.BloodDonation.repository.DoctorRepository;
import com.BloodDonation.BloodDonation.service.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
// un admin service se ocupa de admins, nu de doctori
// un doctor service se ocupa de doctori ;)
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final DoctorRepository doctorRepository;

    public AdminServiceImpl(AdminRepository adminRepository, DoctorRepository doctorRepository) {
        this.adminRepository = adminRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor registerDoctor(Doctor newDoctor) {
        Doctor savedDoctor = doctorRepository.save(newDoctor);
        return savedDoctor;
    }

    @Override
    public Integer deleteDoctorByEmail(String email) {
        Integer deletedDoctor = doctorRepository.deleteByEmail(email);
        return deletedDoctor;
    }

    @Override
    public void updateDoctor(String email, Doctor newDoctor) {
        doctorRepository
                .findByEmail(email)
                .ifPresent(doctor -> {
                    doctor.password = newDoctor.password;
                    doctor.firstName = newDoctor.firstName;
                    doctor.lastName = newDoctor.lastName;

                    doctorRepository.save(doctor);
                });
    }

    @Override
    public Admin getAdmin(UUID userID) {
        Admin admin = adminRepository.findByUuid(userID);
        return null;
    }

    @Override
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }
}

