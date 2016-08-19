package com.registration.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.registration.domain.Event;
import com.registration.domain.User;
import com.registration.repository.EvRepository;
import com.registration.repository.UserRepository;
import com.registration.security.SecurityUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class EventResource {

    @Inject
    private EvRepository eventRepository;

    @Inject
    private UserRepository userRepository;

    @RequestMapping(value = "/events/doctor/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Event> getListEvent(@PathVariable Long id) {
        List<Event> listEvent = eventRepository.findAllEventsForDoctorByIdDoctor(id);
        return listEvent;
    }

    @RequestMapping(value = "/events",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> postEvent(@RequestBody Event event) {
        return userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).map(patient -> {
            //todo
            event.setPatient(patient);
            eventRepository.saveAndFlush(event);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new UsernameNotFoundException("User not found in the database"));
    }

}
