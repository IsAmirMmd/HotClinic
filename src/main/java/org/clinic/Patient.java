package org.clinic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


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

    public ArrayList<String> getMedications() throws SQLException, ClassNotFoundException {
        return File.LoadDrug(this);
    }

    public void addMedication(String medication) throws SQLException, ClassNotFoundException {
        medications = File.LoadDrug(this);
        medications.add(medication);
        File.saveDrug(this,medications);
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
        public Random random = new Random();
        private Date date;
        private long ID;
        private ArrayList<Drug> medication;
        private long doctor;
        private long patientId;

        public String drug1;
        public String drug2;

        public Prescription(Date date, String drug1, String drug2, long doctor, long patient) {
            this.ID = random.nextInt(90000) + 10000;
            this.date = date;
            this.drug1 = drug1;
            this.drug2 = drug2;
            this.doctor = doctor;
            this.patientId = patient;
        }

        public String getDate() {
            return date.toString();
        }

        public ArrayList<Drug> getMedication() {
            return medication;
        }

        public long getDoctor() {
            return doctor;
        }


        public long getPatientId() {
            return patientId;
        }

        public long getID() {
            return ID;
        }

        public void setID(long ID) {
            this.ID = ID;
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
