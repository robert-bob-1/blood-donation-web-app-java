package com.BloodDonation.BloodDonation.controller;

import com.BloodDonation.BloodDonation.entity.Location;
import com.BloodDonation.BloodDonation.entity.users.Doctor;
import com.BloodDonation.BloodDonation.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
