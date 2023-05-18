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

    public boolean removeMedication(String medication) {
        return medications.remove(medication);
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    public boolean removePrescription(Prescription prescription) {
        return prescriptions.remove(prescription);
    }

    public static class Prescription {
        private Date date;
        private String medication;
        private Doctor doctor;
        private String patientName;
        private long patientId;

        public Prescription(Date date, String medication, Doctor doctor, Patient patient) {
            this.date = date;
            this.medication = medication;
            this.doctor = doctor;
            this.patientName = patient.getName();
            this.patientId = patient.getId();
        }

        public Date getDate() {
            return date;
        }

        public String getMedication() {
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
}
