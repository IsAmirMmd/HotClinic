package org.clinic;

import java.sql.SQLException;
import java.util.ArrayList;

interface PatientManagement {
    boolean addPatient(Patient patient);
    boolean removePatient(Patient patient) throws SQLException, ClassNotFoundException;
    ArrayList<Patient> getPatients();
}
