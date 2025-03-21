package org.example.entity;

import org.example.enums.AppointMentStatus;

import java.util.UUID;

public class Appointment {
    String appointMentId;
    Doctor doctor;
    Slots slot;
    Patient patient;
    AppointMentStatus appointMentStatus;
    public Appointment(AppointMentStatus appointMentStatus, Patient patient, Slots slot, Doctor doctor) {
        this.appointMentStatus = appointMentStatus;
        this.patient = patient;
        this.slot = slot;
        this.doctor = doctor;
        this.appointMentId = UUID.randomUUID().toString();
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Slots getSlot() {
        return slot;
    }

    public void setSlot(Slots slot) {
        this.slot = slot;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public AppointMentStatus getAppointMentStatus() {
        return appointMentStatus;
    }

    public String getAppointMentId() {
        return appointMentId;
    }

    public void setAppointMentId(String appointMentId) {
        this.appointMentId = appointMentId;
    }

    public void setAppointMentStatus(AppointMentStatus appointMentStatus) {
        this.appointMentStatus = appointMentStatus;
    }
}
