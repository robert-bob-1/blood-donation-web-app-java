package com.BloodDonation.BloodDonation.controller;

import com.BloodDonation.BloodDonation.entity.Appointment;
import com.BloodDonation.BloodDonation.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
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

    @GetMapping("")
    ResponseEntity<?> getAppointmentsByDonor(@RequestParam("donorId")UUID uuid){
        return ResponseEntity.ok("No appointments for " + "uuid");
    }

    @PostMapping("")
    ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment){
        Appointment newAppointment = appointmentService.addAppointment(appointment);
        return ResponseEntity.ok(newAppointment);
    }
}
