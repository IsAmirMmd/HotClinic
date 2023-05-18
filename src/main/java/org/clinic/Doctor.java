package org.clinic;

import java.util.ArrayList;

public class Doctor extends Person {
    private String specialty;
    private ArrayList<Patient> patients;

    public Doctor(String name, String address, String phone, String specialty) {
        super(name, address, phone);
        this.specialty = specialty;
        this.patients = new ArrayList<>();
    }

    public String getSpecialty() {
        return specialty;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public boolean removePatient(Patient patient) {
        return patients.remove(patient);
    }

    public ArrayList<Patient.Prescription> getPatientPrescriptions(Patient patient) {
        if (patients.contains(patient)) {
            return patient.getPrescriptions();
        }
        return null;
    }

    public Patient.Prescription writePrescription(Patient patient, Drug medication) {
        if (patients.contains(patient)) {
            medication.setQuantity(medication.getQuantity() - 1);
            if (medication.getQuantity() == 0) {
                System.out.println("no more of " + medication.getName() + "!");
            } else {
                Patient.Prescription prescription = new Patient.Prescription(new java.util.Date(), medication.getName(),
                        this, patient);
                patient.addPrescription(prescription);
                return prescription;
            }
        }
        return null;
    }
}
