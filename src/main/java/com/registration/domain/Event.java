package com.registration.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "event")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    private String start_time;// timestamp

    @NotNull
    @Size(min = 1, max = 50)
    private String end_time;

    @ManyToOne(fetch = FetchType.EAGER)
    private User patient;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private User doctor;

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStart() {
        return start_time;
    }

    public void setStart(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd() {
        return end_time;
    }

    public void setEnd(String end_time) {
        this.end_time = end_time;
    }
}
