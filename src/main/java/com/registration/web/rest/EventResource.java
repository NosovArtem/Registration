package com.registration.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.registration.domain.Event;
import com.registration.repository.EvRepository;
import com.registration.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;


@RestController
@RequestMapping("/api")
public class EventResource {

    @Inject
    private EvRepository eventRepository;

    @Inject
    private UserRepository userRepository;

    @RequestMapping(value = "/events/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Event> getListEvent(@PathVariable Long id) {
        List<Event> listEvent = eventRepository.findAllEventsForDoctorByIdDoctor(id);
        return listEvent;
    }

}
