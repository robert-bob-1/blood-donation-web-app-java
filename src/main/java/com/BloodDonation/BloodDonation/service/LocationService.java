package com.BloodDonation.BloodDonation.service;

import com.BloodDonation.BloodDonation.entity.Location;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public interface LocationService {
    ArrayList<Location> getLocations();

    Location addLocation(Location location);

    Integer getNumberOfAppointments(UUID locationId, LocalDate date);
}
