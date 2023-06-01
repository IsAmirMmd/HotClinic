package org.clinic;

import java.sql.SQLException;
import java.util.ArrayList;

interface DrugManagement {
    ArrayList<Drug> getAllDrugs();
    boolean addDrug(Drug drug) throws SQLException, ClassNotFoundException;
    boolean removeDrug(Drug drug) throws SQLException;
}
