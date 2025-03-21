package org.example.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Patient extends Person {
    Set<String> bookedAppointMent;

    public Patient(String name) {
        super(name);
        this.bookedAppointMent = new HashSet<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void addAppointMent(String appointment){
        this.bookedAppointMent.add(appointment);
    }
    public Set<String> getAppointMents(){
        return this.bookedAppointMent;
    }

    public  void cancelAppointMent(String appointmentId){
        this.bookedAppointMent.remove(appointmentId);
    }

}
