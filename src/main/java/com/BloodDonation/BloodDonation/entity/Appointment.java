package com.BloodDonation.BloodDonation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time time;

}