package com.BloodDonation.BloodDonation.mapper;

import com.BloodDonation.BloodDonation.dto.DoctorCreateDTO;
import com.BloodDonation.BloodDonation.entity.users.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public Doctor fromCreateDTOToDoctor(DoctorCreateDTO dto){
        Doctor doctor = new Doctor();
        doctor.email = dto.email;
        doctor.password = dto.password;
        doctor.firstName = dto.firstName;
        doctor.lastName = dto.lastName;
        return doctor;
    }
}
