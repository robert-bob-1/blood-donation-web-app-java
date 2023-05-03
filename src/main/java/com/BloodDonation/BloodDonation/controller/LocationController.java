package com.BloodDonation.BloodDonation.controller;

import com.BloodDonation.BloodDonation.dto.LocationBusyDate;
import com.BloodDonation.BloodDonation.entity.Location;
import com.BloodDonation.BloodDonation.service.LocationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/location")
@CrossOrigin(origins = "http://localhost:4200")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("")
    ResponseEntity<Location[]> getAllLocations(){
        Location[] foundLocations = locationService.getLocations().toArray(new Location[0]);
        if (foundLocations != null)
            return ResponseEntity.ok(foundLocations);
        else
            return (ResponseEntity<Location[]>) ResponseEntity.notFound();
    }

    @PostMapping("")
    ResponseEntity<Location> addLocation(@RequestBody Location location){
        Location newLocation = locationService.addLocation(location);
        return ResponseEntity.ok(newLocation);
    }

    @PostMapping("/free-spots")
    ResponseEntity<Integer> getFreeSpots(@RequestBody Location location,
                                         @RequestParam("date")
                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Integer occupiedSpots = locationService.getNumberOfAppointments(location.getId(), date);

        return ResponseEntity.ok(location.getCapacity() - occupiedSpots);
    }

    @PostMapping("/busy-dates")
    ResponseEntity<List<Object[]>> getBusyDates(@RequestBody Location location) {
        List<Object[]> busyDates = locationService.getBusyDates(location);
        return ResponseEntity.ok(busyDates);
    }

}
