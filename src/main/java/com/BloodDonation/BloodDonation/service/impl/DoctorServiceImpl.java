package com.BloodDonation.BloodDonation.service.impl;

import com.BloodDonation.BloodDonation.entity.users.Doctor;
import com.BloodDonation.BloodDonation.repository.DoctorRepository;
import com.BloodDonation.BloodDonation.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor registerDoctor(Doctor doctor) {
        Doctor savedDoctor = doctorRepository.save(doctor);
        return savedDoctor;
    }

    @Override
    public Doctor getDoctorByEmail(String email) {
        Optional<Doctor> foundDoctor = doctorRepository.findByEmail(email);
        return foundDoctor.orElse(null);
    }


    @Override
    public Doctor deleteDoctorByEmail(String email) {
        return null;
    }

    @Override
    public Doctor updateDoctor(Doctor newDoctor) {
        doctorRepository
                .findById(newDoctor.uuid)
                .ifPresent(doctor -> {
                    doctor.email = newDoctor.email;
                    doctor.password = newDoctor.password;
                    doctor.firstName = newDoctor.firstName;
                    doctor.lastName = newDoctor.lastName;

                    doctorRepository.save(doctor);
                });
        return newDoctor;
    }

    @Override
    public Doctor getDoctorById(UUID uuid) {
        Optional<Doctor> doctor = doctorRepository.findById(uuid);
        return doctor.get();
    }

    @Override
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public void deleteDoctor(UUID uuid) {
        doctorRepository.deleteById(uuid);
    }
}
