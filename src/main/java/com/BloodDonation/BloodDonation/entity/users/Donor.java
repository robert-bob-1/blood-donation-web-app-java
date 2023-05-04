package com.BloodDonation.BloodDonation.entity.users;

import com.BloodDonation.BloodDonation.entity.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Donor")
@Table(name = "donor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Donor extends User {

    @Size(max = 3)
    @NotNull
    public String bloodType;

    @JsonManagedReference
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "user_id")
    private List<Appointment> appointments = new ArrayList<>();
    @Column(name="sms_notification", nullable = false)
    private int smsNotification;
    @Column(name="email_notification", nullable = false)
    private int emailNotification;
    private String phoneNumber;
}
