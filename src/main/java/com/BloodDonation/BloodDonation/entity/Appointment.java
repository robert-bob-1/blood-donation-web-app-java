package com.BloodDonation.BloodDonation.entity;

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

    @Column(name = "doctor_id")
    private UUID doctorId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "location_id")
    private UUID locationId;

    @Column(name = "datetime")
    private Timestamp datetime;

    public Appointment(UUID userId, UUID locationId, String datetime) {
        this.userId = userId;
        this.locationId = locationId;
        this.datetime = Utility.parseTimestamp(datetime);
    }

    public Appointment() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public UUID getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUID doctorId) {
        this.doctorId = doctorId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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