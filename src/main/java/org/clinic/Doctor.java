package org.clinic;

import javax.print.Doc;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

public class Doctor extends Person {
    private String specialty;
    private ArrayList<Patient> patients;

    public Doctor(String name, String address, String phone, String specialty, long salary) {
        super(name, address, phone, salary);
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

    public Patient.Prescription writePrescription(Patient.Prescription tempPrescription, Patient patient, ArrayList<Drug> medication)
            throws SQLException, ClassNotFoundException {
        System.out.println("testDOctro");
        String drug1 = "null";
        String drug2 = "null";
        int i = 0;
        for (Drug drug : medication) {
            if (drug.getQuantity() == 0) {
                medication.remove(drug);
            } else {
                if (i == 0) {
                    drug1 = drug.getName();
                } else {
                    drug2 = drug.getName();
                }
                drug.setQuantity(drug.getQuantity() - 1);
                File.updateDrug(drug);
            }
            i++;
        }
        String dateString = tempPrescription.getDate();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy");
        Date date = new Date();
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateString, dateFormat);
            date = Date.from(localDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        Patient.Prescription prescription = new Patient.Prescription(date, drug1, drug2, this.getId(), patient.getId());
        File.writePrescription(patient, prescription);
        for (Drug drug : medication) {
            patient.addMedication(drug.getName());
        }
        patient.addPrescription(prescription);
        File.removePreDraft(tempPrescription);
        return prescription;
    }


}
