package com.registration.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.registration.domain.Event;
import com.registration.repository.EventRepository;
import com.registration.repository.UserRepository;
import com.registration.security.SecurityUtils;
import com.registration.web.rest.util.HeaderUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class EventResource {

    @Inject
    private EventRepository eventRepository;

    @Inject
    private UserRepository userRepository;

    @RequestMapping(value = "/events/doctor/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Event> getListEvent(@PathVariable Long id) {
        return eventRepository.findAllEventsForDoctorByIdDoctor(id);
    }

    @RequestMapping(value = "/events",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<?> postEvent(@RequestBody Event event) throws URISyntaxException {
        if (eventRepository.findForMatchTimestamp(event.getStart(), event.getDoctor().getId()) != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("scheduleDoctor", "timereserved", "Time is already reserved")).body(null);
        }
        return userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).map(patient -> {
            event.setPatient(patient);
            eventRepository.saveAndFlush(event);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new UsernameNotFoundException("UserPatient not found in the database"));
    }

}
