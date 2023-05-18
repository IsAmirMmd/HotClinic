package org.clinic;

import java.util.ArrayList;

interface DrugManagement {
    ArrayList<Drug> getAllDrugs();
    boolean addDrug(Drug drug);
    boolean removeDrug(Drug drug);
}
