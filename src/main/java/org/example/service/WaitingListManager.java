package org.example.service;

import org.example.entity.Appointment;
import org.example.entity.Slots;
import org.example.enums.AppointMentStatus;

import java.util.*;

public class WaitingListManager {
    static Map<String, Queue<Appointment>> doctorWaitingListQueue = new HashMap<>();
    public static void addToWaitingList(String name, Appointment appointment){
        if(!doctorWaitingListQueue.containsKey(name)){
            doctorWaitingListQueue.put(name, new LinkedList<>());
        }
        doctorWaitingListQueue.get(name).add(appointment);
    }
    public static void allotWaitingListCandidate(String doctor, Slots slots){

        if(!doctorWaitingListQueue.containsKey(doctor) || doctorWaitingListQueue.get(doctor).isEmpty()){
            System.out.println("No patient in waiting list for doctor " + doctor);
            return;
        }
        Appointment appointment = doctorWaitingListQueue.get(doctor).poll();
        System.out.println(appointment.getPatient().getName() + "alloted with slot " + appointment.getSlot() + "for doctor " + appointment.getDoctor().getName());
        appointment.setAppointMentStatus(AppointMentStatus.BOOKED);
        slots.setBooked(true);
        appointment.setSlot(slots);

    }
}
