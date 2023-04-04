package com.BloodDonation.BloodDonation.entity;

import com.BloodDonation.BloodDonation.entity.users.Doctor;
import com.BloodDonation.BloodDonation.entity.users.Donor;
import com.BloodDonation.BloodDonation.entity.utils.Utility;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid", nullable = false)
    private UUID id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Donor donor;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Doctor doctor;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Location location;

    // de ce ai scos legăturile?
    @Column(name = "user_id", nullable = false)
    private UUID userId;
    @Column(name = "doctor_id", nullable = false)
    private UUID doctorId;
    @Column(name = "location_id", nullable = false)
    private UUID locationId;

    @Column(name = "datetime")
    private Timestamp datetime;

    public Appointment() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUID doctorId) {
        this.doctorId = doctorId;
    }

    public UUID getLocationId() {
        return locationId;
    }

    public void setLocationId(UUID locationId) {
        this.locationId = locationId;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }
}