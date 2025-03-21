package org.example.repository;

import org.example.entity.Appointment;
import org.example.entity.Doctor;
import org.example.entity.Patient;
import org.example.enums.Specialization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InmemoryRepository {
     Map<String, Patient> patientDetails;
     Map<String, Doctor> doctorDetails;
     Map<String, Appointment> appointmentDetails;
    private static InmemoryRepository inmemoryRepository;
    private InmemoryRepository(){
        this.doctorDetails = new HashMap<>();
        this.patientDetails = new HashMap<>();
        this.appointmentDetails = new HashMap<>();
    }

    public static  InmemoryRepository getInstance(){
        if(inmemoryRepository == null){
            inmemoryRepository = new InmemoryRepository();
        }
        return inmemoryRepository;
    }

    public void registerDoctor(Doctor doctor){
        this.doctorDetails.put(doctor.getName(), doctor);
    }
    public void removeDoctor(Doctor doctor){
        this.doctorDetails.remove(doctor.getName());
    }
    public void registerPatient(Patient patient){
        this.patientDetails.put(patient.getName(), patient);
    }
    public void removePatient(Patient patient){
        this.patientDetails.remove(patient.getName());
    }
    public Patient getPatientByName(String name){
        return this.patientDetails.get(name);
    }

    public List<String> getAllPatient(){
        return this.patientDetails.keySet().stream().toList();
    }
    public List<String> getAllDoctors(){
        return this.doctorDetails.keySet().stream().toList();
    }


    public  void addAppointMent(Appointment appointment){
        this.appointmentDetails.put(appointment.getAppointMentId(), appointment);
    }
    public void removeAppointment(String appointmentId){
        this.appointmentDetails.remove(appointmentId);
    }
    public Doctor getDoctorByName(String name){
        return this.doctorDetails.get(name);
    }
    public List<String> getDoctorsBySpecialization(Specialization specialization){
        return this.doctorDetails.values().stream().filter(doctor -> doctor.getSpecialization().equals(specialization)).map(doctor->doctor.getName()).toList();
    }

    public Appointment getAppointmentById(String appointmentId) {
        return appointmentDetails.get(appointmentId);
    }

    ;}
