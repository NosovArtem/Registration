package com.registration.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Entity
@Table(name = "event")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "start_time")
    private Date startTime;

    @NotNull
    @Column(name = "end_time")
    private Date endTime;

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

    public Date getStart() {
        return startTime;
    }

    public void setStart(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEnd() {
        return endTime;
    }

    public void setEnd(Date endTime) {
        this.endTime = endTime;
    }

    public String getStartForJS() {
        SimpleDateFormat momentEvent = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        momentEvent.setTimeZone(TimeZone.getTimeZone("GMT+3:00"));
        return momentEvent.format(startTime).replace(" ", "T");
    }

    public String getEndForJS() {
        SimpleDateFormat momentEvent = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        momentEvent.setTimeZone(TimeZone.getTimeZone("GMT+3:00"));
        return momentEvent.format(endTime).replace(" ", "T");

    }
}
