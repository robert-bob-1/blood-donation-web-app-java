package com.BloodDonation.BloodDonation.controller;

import com.BloodDonation.BloodDonation.dto.DoctorCreateDTO;
import com.BloodDonation.BloodDonation.entity.users.Doctor;
import com.BloodDonation.BloodDonation.entity.users.Doctor;
import com.BloodDonation.BloodDonation.entity.users.Doctor;
import com.BloodDonation.BloodDonation.mapper.DoctorMapper;
import com.BloodDonation.BloodDonation.service.DoctorService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = "http://localhost:4200")
public class DoctorController {
    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;

    public DoctorController(DoctorService doctorService, DoctorMapper doctorMapper) {
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
    }

    @GetMapping("/{email}")
    ResponseEntity<Doctor> getDoctorByEmail(@PathVariable("email") String email){
        Doctor foundDoctor = doctorService.getDoctorByEmail(email);
        if (foundDoctor != null)
            return ResponseEntity.ok(foundDoctor);
        else
            return (ResponseEntity<Doctor>) ResponseEntity.notFound();
    }

    @GetMapping("/all")
    ResponseEntity<Doctor[]> getAllDoctors(){
        Doctor[] foundDoctors = doctorService.getDoctors().toArray(new Doctor[0]);
        if (foundDoctors != null)
            return ResponseEntity.ok(foundDoctors);
        else
            return (ResponseEntity<Doctor[]>) ResponseEntity.notFound();
    }

    @PostMapping("/register")
    ResponseEntity<Doctor> registerDoctor(@RequestBody DoctorCreateDTO dto){
        Doctor newDoctor = doctorMapper.fromCreateDTOToDoctor(dto);
        Doctor registeredDoctor = doctorService.registerDoctor(newDoctor);
        return ResponseEntity.ok(registeredDoctor);
    }

    @PutMapping("/edit")
    ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor){
        Doctor updatedDoctor = doctorService.updateDoctor(doctor);
        if (updatedDoctor != null)
            return ResponseEntity.ok(updatedDoctor);
        else
            return (ResponseEntity<Doctor>) ResponseEntity.notFound();
    }

    @DeleteMapping("/delete/{uuid}")
    ResponseEntity<Doctor> deleteDoctor(@PathVariable("uuid") String uuid){
        UUID uuid1 = UUID.fromString(uuid);
        doctorService.deleteDoctor(uuid1);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
