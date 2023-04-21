package com.BloodDonation.BloodDonation.service.impl;

import com.BloodDonation.BloodDonation.entity.Location;
import com.BloodDonation.BloodDonation.repository.LocationRepository;
import com.BloodDonation.BloodDonation.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public ArrayList<Location> getLocations() {
        return (ArrayList<Location>) locationRepository.findAll();
    }

    @Override
    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

}
