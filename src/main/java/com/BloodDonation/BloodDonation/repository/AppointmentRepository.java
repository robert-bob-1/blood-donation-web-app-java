package com.BloodDonation.BloodDonation.repository;

import com.BloodDonation.BloodDonation.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    ArrayList<Appointment> findByUserId(UUID uuid);

    void deleteByUserId(UUID uuid);

    ArrayList<Appointment> findByLocationId(UUID id);

    Integer countByLocationIdAndDate(UUID locationId, LocalDate date);

    @Query("SELECT a.date, :locationCapacity - COUNT(a)"
            + "FROM Appointment a "
            + "WHERE a.locationId = :locationId "
            + "GROUP BY a.date")
    List<Object[]> countAppointmentsByDateAndLocation(
            @Param("locationId") UUID locationId,
            @Param("locationCapacity") int locationCapacity);
}
