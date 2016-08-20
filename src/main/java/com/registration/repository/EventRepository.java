package com.registration.repository;

import com.registration.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event AS e WHERE e.doctor.id = ?1")
    List<Event> findAllEventsForDoctorByIdDoctor(Long user_id);

    @Query("SELECT e.start_time FROM Event AS e WHERE e.start_time = ?1 AND e.doctor.id = ?2")
    String findForMatchTimestamp(String time, Long doctorID);

}

