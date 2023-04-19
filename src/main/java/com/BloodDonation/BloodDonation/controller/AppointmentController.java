package com.BloodDonation.BloodDonation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @GetMapping("")
    ResponseEntity<?> getAppointmentsByDonor(@RequestParam("donorId")UUID uuid){
        return ResponseEntity.ok("No appointments for " + "uuid");
    }
}
