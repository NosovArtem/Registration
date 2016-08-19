package com.registration.repository;

import com.registration.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EvRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e.id, e.patient.lastName, e.start_time, e.end_time FROM Event AS e WHERE e.doctor.id = ?1")
    List<Event> findAllEventsForDoctorByIdDoctor(Long user_id);
}
