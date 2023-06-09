package com.BloodDonation.BloodDonation.repository;

import com.BloodDonation.BloodDonation.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Appointment[] findByDonorId(UUID uuid);

    void deleteByDonor(UUID uuid);

    Appointment[] findByLocationId(UUID id);

    Integer countByLocationIdAndDate(UUID locationId, LocalDate date);

    @Query("SELECT a.date, :locationCapacity - COUNT(a)"
            + "FROM Appointment a "
            + "WHERE a.location.id = :locationId "
            + "GROUP BY a.date")
    List<Object[]> countAppointmentsByDateAndLocation(
            @Param("locationId") UUID locationId,
            @Param("locationCapacity") int locationCapacity);

    Appointment[] findByDate(LocalDate now);
}
