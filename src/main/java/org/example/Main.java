package org.example;

import org.example.entity.Appointment;
import org.example.entity.Doctor;
import org.example.entity.Patient;
import org.example.entity.Slots;
import org.example.enums.Specialization;
import org.example.service.AppointMentManagementService;
import org.example.service.DoctorManagementService;
import org.example.service.PatientManagementService;
import org.example.util.NameRankingStrategy;

import javax.print.Doc;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello, World!");
        AppointMentManagementService appointMentManagementService = AppointMentManagementService.getInstance();
        DoctorManagementService doctorManagementService = DoctorManagementService.getInstance();
        PatientManagementService patientManagementService = PatientManagementService.getInstance();
        Doctor doctor = new Doctor("aman", Specialization.General_Physician);

        Doctor doctor2 = new Doctor("arun", Specialization.Cardiologist);
        Doctor doctor3 = new Doctor("aru2n", Specialization.Cardiologist);
        Doctor doctor4 = new Doctor("aru4n", Specialization.Cardiologist);
        Patient patient1 = new Patient("rahul");
        Patient patient2 = new Patient("anil");
        Patient patient3 = new Patient("oit");

        doctor.setAvaialbility("9:00", "21:00");
        doctor2.setAvaialbility("09:00", "12:00");
        doctor3.setAvaialbility("10:00", "12:00");
        doctor4.setAvaialbility("10:00", "12:00");
//        doctor2.setAvaialbility("10:00", "12:00");

        doctorManagementService.registerDoctor(doctor);
        doctorManagementService.registerDoctor(doctor2);
        doctorManagementService.registerDoctor(doctor3);
        doctorManagementService.registerDoctor(doctor4);
        patientManagementService.registerPatient(patient1);
        patientManagementService.registerPatient(patient2);
        patientManagementService.registerPatient(patient3);

        //
        System.out.println(doctorManagementService.findSlotsBySpecialization(Specialization.Cardiologist));
        System.out.println("-------------");
        Appointment appointment1 = appointMentManagementService.bookApppointment("anil", "arun", new Slots("09:00", "09:30"));
        appointMentManagementService.bookApppointment("oit", "arun", new Slots("09:00", "09:30"));
        appointMentManagementService.bookApppointment("anil" , "aman" , new Slots("10:00", "10:30"));
        appointMentManagementService.bookApppointment("oit", "aru4n", new Slots("12:30", "12:30"));
        appointMentManagementService.cancelAppointMent(appointment1);
        patientManagementService.getPatientAppointMents(patient2.getName());
        System.out.println(doctorManagementService.findSlotsBySpecialization(Specialization.Cardiologist, NameRankingStrategy.getInstance()));
        System.out.println("-------------");
    }
}