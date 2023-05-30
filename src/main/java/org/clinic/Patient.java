package org.clinic;

import java.util.ArrayList;
import java.util.Date;

public class Patient extends Person {
    private String illness;
    private ArrayList<String> medications;
    private ArrayList<Prescription> prescriptions;

    public Patient(String name, String address, String phone, String illness) {
        super(name, address, phone);
        this.illness = illness;
        this.medications = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    public String getIllness() {
        return illness;
    }

    public ArrayList<String> getMedications() {
        return medications;
    }

    public void addMedication(String medication) {
        medications.add(medication);
    }


    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public static class Prescription {
        private Date date;
        private ArrayList<Drug> medication;
        private Doctor doctor;
        private String patientName;
        private long patientId;

        public Prescription(Date date, ArrayList<Drug> medication, Doctor doctor, Patient patient) {
            this.date = date;
            this.medication = medication;
            this.doctor = doctor;
            this.patientName = patient.getName();
            this.patientId = patient.getId();
        }

        public Date getDate() {
            return date;
        }

        public ArrayList<Drug> getMedication() {
            return medication;
        }

        public Doctor getDoctor() {
            return doctor;
        }

        public String getPatientName() {
            return patientName;
        }

        public long getPatientId() {
            return patientId;
        }
    }

    public static ArrayList<Patient> firstLoad() {
        ArrayList<Patient> patients = new ArrayList<>();

        patients.add(new Patient("Ali", "Tehran, Enghelab Street", "09123456789", null));
        patients.add(new Patient("Sara", "Isfahan, Chaharbagh Street", "09123456789", null));
        patients.add(new Patient("Mohammad", "Shiraz, Hafez Street", "09123456789", null));
        patients.add(new Patient("Maryam", "Tabriz, Saat Square", "09123456789", null));
        patients.add(new Patient("Hossein", "Mashhad, Imam Reza Street", "09123456789", null));
        return patients;
    }
}
