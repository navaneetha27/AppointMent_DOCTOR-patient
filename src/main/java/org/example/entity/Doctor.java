package org.example.entity;

import org.example.enums.Specialization;
import org.example.util.SlotHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Doctor extends  Person {

    Specialization specialization;
   Set<String> appointments;

    public void setAppointments(Set<String> appointments) {
        this.appointments = appointments;
    }

    List<Slots> availableSlots;
    double rating;


    public Doctor(String name, Specialization specialization) {
        super(name);
        this.specialization = specialization;
        this.appointments = new HashSet<>();
    }

    void setAvailableSlots(List<Slots> availableSlots){
        this.availableSlots = availableSlots;

    }
    public void setAvaialbility(String startTime, String endTime){
        this.availableSlots = SlotHelper.generateSlots(startTime, endTime);
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Slots> getAvailableSlots() {
        return availableSlots;
    }

    public Set<String> getAppointments() {
        return appointments;
    }

    public void addAppointMent(String appointmentId) {
        this.appointments.add(appointmentId);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public  String toString(){
        return this.name + " Specialization :" + this.specialization + " :: " + this.rating +  "*" ;
    }
}
