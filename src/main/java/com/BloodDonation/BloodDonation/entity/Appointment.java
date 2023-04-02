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

    @Column(name = "datetime")
    private Timestamp datetime;

    public Appointment(Donor donor, Location location, String datetime) {
//        this.donor = donor;
//        this.location = location;
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

//    public Donor getDonor() {
//        return donor;
//    }
//
//    public void setDonor(Donor donor) {
//        this.donor = donor;
//    }
//
//    public Doctor getDoctor() {
//        return doctor;
//    }
//
//    public void setDoctor(Doctor doctor) {
//        this.doctor = doctor;
//    }
//
//    public Location getLocation() {
//        return location;
//    }
//
//    public void setLocation(Location location) {
//        this.location = location;
//    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }
}