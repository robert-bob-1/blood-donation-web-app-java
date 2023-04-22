package com.BloodDonation.BloodDonation.entity;

import com.BloodDonation.BloodDonation.entity.utils.Utility;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
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

    @Column(name = "user_id", nullable = false)
    private UUID userId;
    @Column(name = "doctor_id", nullable = false)
    private UUID doctorId;
    @Column(name = "location_id", nullable = false)
    private UUID locationId;
    private Date date;
    private Time time;

    public Appointment(UUID uuid, UUID id, String date) {
        this.userId = uuid;
        this.locationId = id;
        this.date = Utility.parseDate(date);
    }

    public void setDate(String date) {
        this.date = Utility.parseDate(date);
    }

    public void setTime(String time) {
        this.time = Utility.parseTime(time);
    }
}