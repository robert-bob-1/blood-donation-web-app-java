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
        return ResponseEntity.ok(foundAppointments);
    }

    @GetMapping("/location/{locationId}")
    ResponseEntity<?> getAppointmentsAtLocation(@PathVariable UUID locationId){
        Appointment[] foundAppointments = appointmentService.getAppointmentsByLocation(locationId);
        return ResponseEntity.ok(foundAppointments);
    }

    @PutMapping("")
    ResponseEntity<?> confirmAppointment(
            @RequestBody Appointment appointment) {
        try {
            appointmentService.updateAppointment(appointment);
            return ResponseEntity.ok("Validated appointment");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
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
