package com.registration.web.rest;

import com.registration.Application;
import com.registration.domain.Event;
import com.registration.domain.User;
import com.registration.repository.EventRepository;
import com.registration.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UserResource REST controller.
 *
 * @see EventResourceIntTest
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class EventResourceIntTest {

    private static Date START;
    private static Date END;
    private User doctor;
    private Event event;

    @Inject
    private UserRepository userRepository;

    @Inject
    private EventRepository eventRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() throws ParseException {
        EventResource eventResource = new EventResource();
        ReflectionTestUtils.setField(eventResource, "userRepository", userRepository);
        ReflectionTestUtils.setField(eventResource, "eventRepository", eventRepository);
        this.mockMvc = MockMvcBuilders.standaloneSetup(eventResource).build();

        List<User> doctors = userRepository.findDoctors();
        doctor = doctors.stream().findFirst().get();
        START = new SimpleDateFormat("dd MM yyyy HH:mm:ss").parse("27 09 2017 20:29:30");
        END = new SimpleDateFormat("dd MM yyyy HH:mm:ss").parse("27 09 2017 21:29:30");

        event = new Event();
        event.setPatient(doctor);
        event.setDoctor(doctor);
        event.setStart(START);
        event.setEnd(END);
    }

    @Test
    @Transactional
    public void testGetEvents() throws Exception {
        eventRepository.saveAndFlush(event);

        mockMvc.perform(get("/api/events/doctor/{id}", doctor.getId())
            .accept(MediaType.APPLICATION_JSON)).andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.[0].startForJS").value(event.getStartForJS()))
            .andExpect(jsonPath("$.[0].endForJS").value(event.getEndForJS()))
            .andExpect(jsonPath("$.[0].doctor.lastName").value(event.getDoctor().getLastName()))
            .andExpect(jsonPath("$.[0].doctor.firstName").value(event.getDoctor().getFirstName()))
            .andExpect(jsonPath("$.[0].patient.firstName").value(event.getPatient().getFirstName()))
            .andExpect(jsonPath("$.[0].patient.firstName").value(event.getPatient().getFirstName()))
            .andExpect(jsonPath("$.[0].patient.id").value(event.getPatient().getId().intValue()))
            .andExpect(jsonPath("$.[0].doctor.id").value(event.getDoctor().getId().intValue()));
    }
}
