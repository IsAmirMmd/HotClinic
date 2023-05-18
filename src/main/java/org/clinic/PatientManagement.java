package org.clinic;

import java.util.ArrayList;

interface PatientManagement {
    boolean addPatient(Patient patient);
    boolean removePatient(Patient patient);
    ArrayList<Patient> getPatients();
}
