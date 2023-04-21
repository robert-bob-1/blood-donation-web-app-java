package com.BloodDonation.BloodDonation.service;

import com.BloodDonation.BloodDonation.entity.Location;

import java.util.ArrayList;

public interface LocationService {
    ArrayList<Location> getLocations();

    Location addLocation(Location location);
}
