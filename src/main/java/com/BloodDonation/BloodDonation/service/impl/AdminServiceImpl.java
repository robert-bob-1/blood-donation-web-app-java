package com.BloodDonation.BloodDonation.service.impl;

import com.BloodDonation.BloodDonation.entity.Admin;
import com.BloodDonation.BloodDonation.entity.Doctor;
import com.BloodDonation.BloodDonation.repository.AdminRepository;
import com.BloodDonation.BloodDonation.repository.DoctorRepository;
import com.BloodDonation.BloodDonation.service.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
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
    public Doctor updateDoctor(Doctor newDoctor) {
        return null;
    }

    @Override
    public Admin getAdmin(UUID userID) {
        Admin admin = adminRepository.findByUuid(userID);
        return null;
    }
}

