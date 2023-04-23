package com.BloodDonation.BloodDonation.controller;

import com.BloodDonation.BloodDonation.entity.Appointment;
import com.BloodDonation.BloodDonation.service.AppointmentService;
import org.atmosphere.config.service.Delete;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{donorId}")
    ResponseEntity<?> getAppointmentsByDonor(@PathVariable UUID donorId){
        Appointment[] foundAppointments = appointmentService.getAppointmentsByDonor(donorId);
        for( int i = 0; i < foundAppointments.length; i++)
            System.out.println(foundAppointments[i]);
        return ResponseEntity.ok(foundAppointments);
    }

    @PostMapping("")
    ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment){
        Appointment newAppointment = appointmentService.addAppointment(appointment);
        return ResponseEntity.ok(newAppointment);
    }

    @DeleteMapping("/{appointmentId}")
    ResponseEntity<?> deleteAppointment(@PathVariable("appointmentId") UUID id){
        appointmentService.deleteAppointment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
