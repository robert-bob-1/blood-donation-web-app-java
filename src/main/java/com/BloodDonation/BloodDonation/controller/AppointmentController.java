package com.BloodDonation.BloodDonation.controller;

import com.BloodDonation.BloodDonation.entity.Appointment;
import com.BloodDonation.BloodDonation.service.AppointmentService;
import org.atmosphere.config.service.Delete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        try {
            Appointment[] foundAppointments = appointmentService.getAppointmentsByDonor(donorId);
            return ResponseEntity.ok(foundAppointments);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @GetMapping("/location/{locationId}")
    ResponseEntity<?> getAppointmentsAtLocation(@PathVariable UUID locationId){
        Appointment[] foundAppointments = appointmentService.getAppointmentsByLocation(locationId);
        return ResponseEntity.ok(foundAppointments);
    }

    @GetMapping("/paginated")
    Page<Appointment> getAppointmentsPaginated(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Appointment> foundAppointments = appointmentService.getAppointments(pageable);
        return foundAppointments;
    }

    @GetMapping("/today")
    ResponseEntity<?> getAppointmentsToday(){
        try {
            Appointment[] foundAppointments = appointmentService.getAppointmentsToday();
            return ResponseEntity.ok(foundAppointments);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
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
    ResponseEntity<?> addAppointment(@RequestBody Appointment appointment){
        try {
            Appointment newAppointment = appointmentService.addAppointment(appointment);
            return ResponseEntity.ok(newAppointment);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @DeleteMapping("/{appointmentId}")
    ResponseEntity<?> deleteAppointment(@PathVariable("appointmentId") UUID id){
        try {
            appointmentService.deleteAppointment(id);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
