package org.example.service;

import org.example.entity.Appointment;
import org.example.entity.Doctor;
import org.example.entity.Patient;
import org.example.entity.Slots;
import org.example.enums.AppointMentStatus;
import org.example.repository.InmemoryRepository;

import java.util.Collection;
import java.util.Objects;

public class AppointMentManagementService {
    InmemoryRepository inmemoryRepository;
    private static AppointMentManagementService appointMentManagementService;
    private PatientManagementService patientManagementService;
    private DoctorManagementService doctorManagementService;
    private AppointMentManagementService() {
        this.inmemoryRepository = InmemoryRepository.getInstance();
        this.doctorManagementService = DoctorManagementService.getInstance();
        this.patientManagementService = PatientManagementService.getInstance();
    }
    public static AppointMentManagementService getInstance(){
        if(appointMentManagementService == null){
            appointMentManagementService = new AppointMentManagementService();
        }
        return appointMentManagementService;
    }

    public  Appointment bookApppointment(String patientName, String doctorName, Slots slots){
        boolean isOverlap = validatePatientHasAppointmentOnThatTime(patientName, slots);
        if(isOverlap){
            System.out.println("Sorry You have already  have a booking for this time");
            return null;
        }
        Patient patient = patientManagementService.getPatient(patientName);
        Doctor doctor = doctorManagementService.getDoctorByName(doctorName);
        boolean slotAvailable = false;
        for(Slots slots1 : doctor.getAvailableSlots()){
            if(Objects.equals(slots1.getStartTime(), slots.getStartTime()) && !slots1.isBooked()){
              slots1.setBooked(true);
                slotAvailable = true;

            }
        }
        Appointment appointment = new Appointment(AppointMentStatus.WAITING_LIST, patient, slots, doctor);
        if(!slotAvailable){
            WaitingListManager.addToWaitingList(doctor.getName(),appointment);
        }
        else{
            slots.setBooked(true);
            appointment.setAppointMentStatus(AppointMentStatus.BOOKED);
        }
        inmemoryRepository.addAppointMent(appointment);
        patient.addAppointMent(appointment.getAppointMentId());
        doctor.addAppointMent(appointment.getAppointMentId());
        return appointment;
    }

    private boolean validatePatientHasAppointmentOnThatTime(String patientName, Slots slots) {
        Collection<String> appointmentIds = patientManagementService.getPatientAppointMents(patientName);
        for(String appointmentId : appointmentIds){
            Appointment appointment = inmemoryRepository.getAppointmentById(appointmentId);
            if(appointment.getSlot().getStartTime().equals(slots.getStartTime()) && appointment.getSlot().isBooked()){
                return true;
            }
        }
        return false;
    }

    public void cancelAppointMent(Appointment appointment){
        DoctorManagementService doctorManagementService = DoctorManagementService.getInstance();
        PatientManagementService patientManagementService = PatientManagementService.getInstance();
        Doctor doctor = appointment.getDoctor();
        Patient patient = appointment.getPatient();
        Slots slots = appointment.getSlot();
        slots.setBooked(false);
        doctor.getAppointments().remove(appointment.getAppointMentId());
        patient.cancelAppointMent(appointment.getAppointMentId());
        WaitingListManager.allotWaitingListCandidate(doctor.getName(),slots);
    }
}
