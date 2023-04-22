package com.BloodDonation.BloodDonation.service.impl;

import com.BloodDonation.BloodDonation.entity.Location;
import com.BloodDonation.BloodDonation.repository.AppointmentRepository;
import com.BloodDonation.BloodDonation.repository.LocationRepository;
import com.BloodDonation.BloodDonation.service.LocationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final AppointmentRepository appointmentRepository;

    public LocationServiceImpl(LocationRepository locationRepository,
                               AppointmentRepository appointmentRepository) {
        this.locationRepository = locationRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public ArrayList<Location> getLocations() {
        return (ArrayList<Location>) locationRepository.findAll();
    }

    @Override
    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Integer getNumberOfAppointments(UUID locationId, LocalDate date) {
        return appointmentRepository.countByLocationIdAndDate(locationId, date);
    }

}
