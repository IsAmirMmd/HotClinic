package org.clinic;

import javax.print.Doc;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class BaseMenu {
    public static int num;
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Drug> drugs;
    public static ArrayList<Doctor> doctors;
    public static ArrayList<Nurse> nurses;
    public static ArrayList<Patient> patients;
    public static ArrayList<Personnel> personnels;
    public static Manager manager;

    static {
        try {
            doctors = File.LoadDoctor();
            nurses = File.LoadNurse();
            patients = File.LoadPatient();
            personnels = File.LoadPersonnel();
            drugs = File.LoadFromDrug();

            manager = new Manager("amirMmd", null, null, "N.I.T", patients, doctors, nurses, drugs, personnels);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void baseMenu() throws SQLException, ClassNotFoundException {
        System.out.println("1. manager panel");
        System.out.println("2. patient panel");
        System.out.println("3. doctor panel");
        System.out.println("0. Exit");
        num = scanner.nextInt();
        try {

            switch (num) {
                case 1:
                    managerLogin();
                    break;
                case 2:
                    patientPanel();
                    break;
                case 3:
                    doctorPanel(manager);
                case 0:
                    throw new RuntimeException("Exit From Menu");
            }
        } catch (RuntimeException e) {
            System.out.println("Caught Your Order : " + e.getMessage());
        }
    }

    public static void managerLogin() throws SQLException, ClassNotFoundException {
        clearConsole();
        boolean IsAllowed = false;
        Scanner admin = new Scanner(System.in);
        System.out.println("Enter Admin UserName:");
        String userName = admin.nextLine();
        System.out.println("Enter Admin passWord:");
        String passWord = admin.nextLine();
        if (userName.equals("admin") && passWord.equals("admin")) {
            System.out.println("you enter data correctly");
            managerPanel(manager);
        } else {
            try {
                throw new RuntimeException("username or password is wrong");
            } catch (RuntimeException e) {
                System.out.println("Error In Login : " + e.getMessage());
                managerPanel(manager);
            }
        }
    }

    public static void managerPanel(Manager manager) throws SQLException, ClassNotFoundException {
        clearConsole();
        System.out.println("**** welcome manager ****");
        System.out.println("select action from menu :");
        System.out.println("1. manage doctors");
        System.out.println("2. manage nurses");
        System.out.println("3. manage patient");
        System.out.println("4. manage personnels");
        System.out.println("5. manage drugs");
        num = scanner.nextInt();
        switch (num) {
            case 1:
                manageDoctor();
                break;

            case 2:
                manageNurses();
                break;

            case 3:
                managePatient();
                break;

            case 4:
                managePersonnel();
                break;
            case 5:
                manageDrugs();
                break;
        }
    }

    public static void manageDoctor() throws SQLException, ClassNotFoundException {
        clearConsole();
        System.out.println("**** Doctor menu ****");
        System.out.println("1. Add Doctor");
        System.out.println("2. Remove Doctor");
        System.out.println("3. Show All Doctors");
        System.out.println("4. Checking Doctor Requests");
        System.out.println("0. Back");
        num = scanner.nextInt();

        switch (num) {
            case 1:
                addDoctor();
                break;

            case 2:
                removeDoctor();
                break;

            case 3:
                showAllDoctor();
                break;
            case 4:
                doctorReq(manager);
                break;
            case 0:
                managerPanel(manager);
                break;
        }
    }

    public static void addDoctor() throws SQLException, ClassNotFoundException {
        clearConsole();
        Scanner add = new Scanner(System.in);
        System.out.println("Enter doctor name: ");
        String name = add.nextLine();

        System.out.println("Enter doctor address: ");
        String address = add.nextLine();

        System.out.println("Enter doctor phone: ");
        String phone = add.nextLine();

        System.out.println("Enter doctor speciality: ");
        String speciality = add.nextLine();

        System.out.println("Enter min salary: ");
        long salary = add.nextLong();

        Doctor doctor = new Doctor(name, address, phone, speciality, salary);
        manager.addDoctor(doctor);

        System.out.println("new doctor with name " + name + " added!");
        sleepTime(2000);
        manageDoctor();
    }

    public static void removeDoctor() throws SQLException, ClassNotFoundException {
        clearConsole();
        Scanner remove = new Scanner(System.in);
        System.out.print("Enter doctor name: ");
        String name = remove.nextLine();

        Doctor doctorToRemove = null;
        for (Doctor doctor : doctors) {
            if (doctor.getName().equals(name)) {
                doctorToRemove = doctor;
                break;
            }
        }

        try {
            if (doctorToRemove == null) {
                throw new RuntimeException("Doctor not found.");
            } else {
                manager.removeDoctor(doctorToRemove);
                System.out.println("the doctor with name " + name + " removed!");
            }
        } catch (RuntimeException e) {
            System.out.println("Error In Finding Doctor : " + e.getMessage());
        }
        sleepTime(2000);
        manageDoctor();
    }

    public static void showAllDoctor() throws SQLException, ClassNotFoundException {
        clearConsole();
        System.out.println("showing all doctors...");
        for (Doctor doctor : doctors) {
            System.out.println("ID         :  " + doctor.getId());
            System.out.println("name       :  " + doctor.getName());
            System.out.println("address    :  " + doctor.getAddress());
            System.out.println("phone      :  " + doctor.getPhone());
            System.out.println("speciality :  " + doctor.getSpecialty());
            System.out.println("      *******************     ");
        }
        System.out.println("");
        System.out.println("0. back");
        num = scanner.nextInt();
        switch (num) {
            case 0:
                manageDoctor();
                break;
        }
    }

    public static void connectionRequest(Manager manager) throws SQLException, ClassNotFoundException {
        Scanner submit = new Scanner(System.in);

        System.out.println("what do you want to do?");
        System.out.println("1. check requests");
        System.out.println("0. back");

        int num = submit.nextInt();

        switch (num) {
            case 1:
                doctorReq(manager);
                break;
            case 0:
                manageDoctor();
                break;
        }
    }

    public static void doctorReq(Manager manager) throws SQLException, ClassNotFoundException {
        clearConsole();

        Scanner submit = new Scanner(System.in);

        System.out.println("here is all of requests");
        System.out.println("    *********");

        for (Doctor doctor : File.LoadDoctors()) {
            System.out.println("ID         :  " + doctor.getId());
            System.out.println("name       :  " + doctor.getName());
            System.out.println("address    :  " + doctor.getAddress());
            System.out.println("phone      :  " + doctor.getPhone());
            System.out.println("speciality :  " + doctor.getSpecialty());
            System.out.println("      *******************     ");
        }
        System.out.println("finished result...");
        System.out.println("Enter Doctor ID To Do Remove/Submit");
        int DoctorID = submit.nextInt();
//        fetch Doctor
        boolean IsValid = false;
        Doctor tempDoctor = null;
        for (Doctor doctor : File.LoadDoctors()) {
            if (doctor.getId() == DoctorID) {
                IsValid = true;
                tempDoctor = doctor;
            }
        }
        try {
            if (IsValid) {
            } else {
                throw new RuntimeException("ID isn't valid!");
            }
        } catch (RuntimeException e) {
            System.out.println("Error in Finding Doctor with this ID : " + e.getMessage());
            sleepTime(1500);
            doctorReq(manager);
        }
//      needed action ...
        boolean IsValidPage = false;
        System.out.println("what do you want to do?");
        System.out.println("1. submit");
        System.out.println("2. remove");
        int pageID = submit.nextInt();
        if (pageID == 1 || pageID == 2) {
            IsValidPage = true;
        }
        try {
            if (IsValidPage) {
                switch (pageID) {
                    case 1:
                        acceptDoctor(tempDoctor, manager);
                        break;
                    case 2:
                        removeReq(tempDoctor);
                        break;
                }
            } else {
                throw new RuntimeException("page number isn't valid!");
            }
        } catch (RuntimeException e) {
            System.out.println("Error in Finding Page: " + e.getMessage());
            sleepTime(1500);
            doctorReq(manager);
        }

    }


    public static void acceptDoctor(Doctor tempDoctor, Manager manager) throws SQLException, ClassNotFoundException {
        Scanner income = new Scanner(System.in);

        System.out.println("Enter Salary for Dr." + tempDoctor.getName());
        System.out.println("Speciality  : " + tempDoctor.getSpecialty());
        System.out.println("0. back and select another one");
        int Salary = income.nextInt();
        if (Salary == 0) {
            doctorReq(manager);
        } else {
            tempDoctor.setSalary(Salary);

            manager.addDoctor(tempDoctor);
            File.removeDraft(tempDoctor);

            System.out.println("Doctor With Name, Dr." + tempDoctor.getName() + " added to DoctorList");

            connectionRequest(manager);
        }


    }

    public static void removeReq(Doctor doctor) throws SQLException, ClassNotFoundException {
        Scanner income = new Scanner(System.in);
        System.out.println("Are You Sure for removing request from Dr."+doctor.getName()+" ?");
        System.out.println("0. back and select another one");
        String answer = income.nextLine();
        if (answer == "0") {
            doctorReq(manager);
        } else {
            File.removeDraft(doctor);

            System.out.println("doctor request with ID : " + doctor.getId() + " removed successfully");

            connectionRequest(manager);
        }

    }

    public static void manageNurses() throws SQLException, ClassNotFoundException {
        clearConsole();
        System.out.println("**** Nurse menu ****");
        System.out.println("1. Add Nurse");
        System.out.println("2. Remove Nurse");
        System.out.println("3. Show All Nurses");
        System.out.println("0. Back");
        num = scanner.nextInt();

        switch (num) {
            case 1:
                addNurse();
                break;
            case 2:
                removeNurse();
                break;

            case 3:
                showAllNurse();
                break;

            case 0:
                managerPanel(manager);
                break;
        }
    }

    public static void addNurse() throws SQLException, ClassNotFoundException {
        clearConsole();
        Scanner add = new Scanner(System.in);
        System.out.println("Enter nurse name: ");
        String name = add.nextLine();

        System.out.println("Enter nurse address: ");
        String address = add.nextLine();

        System.out.println("Enter nurse phone: ");
        String phone = add.nextLine();

        System.out.println("Enter min salary: ");
        long salary = add.nextLong();

        Nurse nurse = new Nurse(name, address, phone, salary);
        manager.addNurse(nurse);

        System.out.println("new nurse with name " + name + " added!");
        sleepTime(2500);
        manageNurses();
    }

    public static void removeNurse() throws SQLException, ClassNotFoundException {
        clearConsole();
        Scanner remove = new Scanner(System.in);
        System.out.print("Enter nurse name: ");
        String name = remove.nextLine();

        Nurse nurseToRemove = null;
        for (Nurse nurse : nurses) {
            if (nurse.getName().equals(name)) {
                nurseToRemove = nurse;
                break;
            }
        }

        try {
            if (nurseToRemove == null) {
                throw new RuntimeException("Nurse not found.");
            } else {
                manager.removeNurse(nurseToRemove);
                System.out.println("the nurse with name " + name + " removed!");
            }
        } catch (RuntimeException e) {
            System.out.println("Error In finding Nurse : " + e.getMessage());
        }
        sleepTime(2500);
        manageNurses();
    }

    public static void showAllNurse() throws SQLException, ClassNotFoundException {
        clearConsole();
        System.out.println("showing all nurses...");
        for (Nurse nurse : nurses) {
            System.out.println("ID         :  " + nurse.getId());
            System.out.println("name       :  " + nurse.getName());
            System.out.println("address    :  " + nurse.getAddress());
            System.out.println("phone      :  " + nurse.getPhone());
            System.out.println("     *******************     ");
        }
        System.out.println("");
        System.out.println("0. back");
        num = scanner.nextInt();
        switch (num) {
            case 0:
                manageNurses();
                break;
        }
    }


    public static void managePatient() throws SQLException, ClassNotFoundException {
        clearConsole();
        System.out.println("**** patients menu ****");
        System.out.println("1. Add Patients");
        System.out.println("2. Remove Patient");
        System.out.println("3. Show All Patients");
        System.out.println("0. Back");
        num = scanner.nextInt();
        switch (num) {
            case 1:
                addPatients();
                break;

            case 2:
                removePatients();
                break;

            case 3:
                showAllPatients();
                break;

            case 0:
                managerPanel(manager);
                break;
        }
    }

    public static void addPatients() throws SQLException, ClassNotFoundException {
        clearConsole();
        Scanner add = new Scanner(System.in);
        System.out.println("Enter patient name: ");
        String name = add.nextLine();

        System.out.println("Enter patient address: ");
        String address = add.nextLine();

        System.out.println("Enter patient phone: ");
        String phone = add.nextLine();

        Patient patient = new Patient(name, address, phone, null);


        manager.addPatient(patient);

        System.out.println("new patient with name " + name + " added!");
        sleepTime(2500);
        managePatient();
    }

    public static void removePatients() throws SQLException, ClassNotFoundException {
        clearConsole();
        Scanner remove = new Scanner(System.in);
        System.out.print("Enter patient name: ");
        String name = remove.nextLine();

        Patient patientToRemove = null;
        for (Patient patient : patients) {
            if (patient.getName().equals(name)) {
                patientToRemove = patient;
                break;
            }
        }

        try {
            if (patientToRemove == null) {
                throw new RuntimeException("Patient not found.");
            } else {
                manager.removePatient(patientToRemove);
                System.out.println("the patient with name " + name + " removed!");
            }
        } catch (RuntimeException e) {
            System.out.println("Error In Finding Patient : " + e.getMessage());
        }
        sleepTime(2000);
        managePatient();
    }


    public static void showAllPatients() throws SQLException, ClassNotFoundException {
        clearConsole();
        System.out.println("showing all patients...");
        for (Patient patient : patients) {
            System.out.println("ID         :  " + patient.getId());
            System.out.println("name       :  " + patient.getName());
            System.out.println("address    :  " + patient.getAddress());
            System.out.println("phone      :  " + patient.getPhone());
            System.out.println("illness    :  " + patient.getIllness());
            System.out.println("     *******************     ");
        }
        System.out.println("");
        System.out.println("0. back");
        num = scanner.nextInt();
        switch (num) {
            case 0:
                managePatient();
                break;
        }
    }

    public static void managePersonnel() throws SQLException, ClassNotFoundException {
        clearConsole();
        System.out.println("**** Personnel menu ****");
        System.out.println("1. Add Personnel");
        System.out.println("2. Remove Personnel");
        System.out.println("3. Show All Personnels");
        System.out.println("0. Back");
        num = scanner.nextInt();

        switch (num) {
            case 1:
                addPersonnel();
                break;

            case 2:
                removePersonnel();
                break;

            case 3:
                showAllPersonnel();
                break;

            case 0:
                managerPanel(manager);
                break;
        }
    }

    public static void addPersonnel() throws SQLException, ClassNotFoundException {
        clearConsole();
        Scanner add = new Scanner(System.in);
        System.out.println("Enter personnel name: ");
        String name = add.nextLine();

        System.out.println("Enter personnel address: ");
        String address = add.nextLine();

        System.out.println("Enter personnel phone: ");
        String phone = add.nextLine();

        System.out.println("Enter personnel task: ");
        String task = add.nextLine();

        System.out.println("Enter min salary: ");
        long salary = add.nextLong();

        Personnel personnel = new Personnel(name, address, phone, salary, task);

        manager.addPersonnel(personnel);

        System.out.println("new personnel with name " + name + " added!");
        sleepTime(2000);
        managePersonnel();
    }

    public static void removePersonnel() throws SQLException, ClassNotFoundException {
        clearConsole();
        Scanner remove = new Scanner(System.in);
        System.out.print("Enter personnel name: ");
        String name = remove.nextLine();

        Personnel personnelToRemove = null;
        for (Personnel personnel : personnels) {
            if (personnel.getName().equals(name)) {
                personnelToRemove = personnel;
                break;
            }
        }

        try {
            if (personnelToRemove == null) {
                throw new RuntimeException("personnel not found.");
            } else {
                manager.removePersonnel(personnelToRemove);
                System.out.println("the personnel with name " + name + " removed!");
            }
        } catch (RuntimeException e) {
            System.out.println("Error In Finding Personnel : " + e.getMessage());
        }
        sleepTime(2000);
        managePersonnel();
    }

    public static void showAllPersonnel() throws SQLException, ClassNotFoundException {
        clearConsole();
        System.out.println("showing all personnel...");
        for (Personnel personnel : personnels) {
            System.out.println("ID         :  " + personnel.getId());
            System.out.println("name       :  " + personnel.getName());
            System.out.println("address    :  " + personnel.getAddress());
            System.out.println("phone      :  " + personnel.getPhone());
            System.out.println("task       :  " + personnel.getTask());
            System.out.println("salary     :  " + personnel.getSalary());
            System.out.println("     *******************     ");
        }
        System.out.println("");
        System.out.println("0. back");
        num = scanner.nextInt();
        switch (num) {
            case 0:
                managePersonnel();
                break;
        }
    }

    public static void manageDrugs() throws SQLException, ClassNotFoundException {
        clearConsole();
        System.out.println("**** DrugStore menu ****");
        System.out.println("1. Add Drugs");
        System.out.println("2. Remove Drugs");
        System.out.println("3. Show All Drugs");
        System.out.println("0. Back");
        num = scanner.nextInt();

        switch (num) {
            case 1:
                addDrug();
                break;

            case 2:
                removeDrug();
                break;

            case 3:
                showAllDrugs();
                break;

            case 0:
                managerPanel(manager);
                break;
        }
    }

    public static void addDrug() throws SQLException, ClassNotFoundException {
        clearConsole();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the drug: ");
        String name = scanner.nextLine();
        System.out.print("Enter the quantity of the drug: ");
        int quantity = scanner.nextInt();

        Drug drug = new Drug(name, quantity);

        manager.addDrug(drug);

        System.out.println("new drug with name " + name + " added!");
        sleepTime(2500);
        manageDrugs();
    }

    public static void removeDrug() throws SQLException, ClassNotFoundException {
        clearConsole();
        Scanner remove = new Scanner(System.in);
        System.out.print("Enter drug name: ");
        String name = remove.nextLine();

        Drug drugToRemove = null;
        for (Drug drug : drugs) {
            if (drug.getName().equals(name)) {
                drugToRemove = drug;
                break;
            }
        }

        try {
            if (drugToRemove == null) {
                throw new RuntimeException("Drug not found.");
            } else {
                manager.removeDrug(drugToRemove);
                System.out.println("the drug with name " + name + " removed!");
            }
        } catch (RuntimeException e) {
            System.out.println("Error in Finding Drug : " + e.getMessage());
        }
        sleepTime(2000);
        manageDrugs();
    }

    public static void showAllDrugs() throws SQLException, ClassNotFoundException {
        clearConsole();
        System.out.println("showing all drugs...");
        for (Drug drug : drugs) {
            System.out.println("ID                :  " + drug.getUid());
            System.out.println("name              :  " + drug.getName());
            System.out.println("quantity          :  " + drug.getQuantity());
            System.out.println("availability      :  " + drug.isAvailable());
            System.out.println("     *******************     ");
        }
        System.out.println("");
        System.out.println("0. back");
        num = scanner.nextInt();
        switch (num) {
            case 0:
                manageDrugs();
                break;
        }
    }

    public static void patientPanel() throws SQLException, ClassNotFoundException {
        clearConsole();
        System.out.println("**** welcome dear ****");
        System.out.println("select action from menu :");
        System.out.println("1. submit your details");
        System.out.println("2. Login to Your account");
        num = scanner.nextInt();
        switch (num) {
            case 1:
                submitPatient(manager);
                break;
            case 2:
                patientLogin();
                break;
        }
    }

    public static void submitPatient(Manager manager) throws SQLException, ClassNotFoundException {
        clearConsole();
        boolean IsValid = true;
        Scanner add = new Scanner(System.in);
        System.out.println("Enter your userName: ");
        String name = add.nextLine();

        System.out.println("Enter your address: ");
        String address = add.nextLine();

        System.out.println("Enter your phone: ");
        String phone = add.nextLine();
        for (Patient patient : patients) {
            try {
                if (name.equals(patient.getName())) {
                    throw new RuntimeException("please select another userName!");
                }
            } catch (RuntimeException e) {
                System.out.println("Error In Taken UserName : " + e.getMessage());
                IsValid = false;
            }

        }
        if (IsValid) {
            Patient patient = new Patient(name, address, phone, null);
            manager.addPatient(patient);
            patientLogin();
        } else {
            submitPatient(manager);
        }

    }

    public static void patientLogin() throws SQLException, ClassNotFoundException {
        clearConsole();
        boolean IsValid = false;
        Patient tempPatient = null;
        System.out.println("Enter Your Name :");
        Scanner login = new Scanner(System.in);
        String name = login.nextLine();
        for (Patient patient : patients) {
            if (name.equals(patient.getName())) {
                tempPatient = patient;
                IsValid = true;
            }
        }
        if (IsValid) {
            patientCenter(tempPatient);
        } else {
            try {
                throw new RuntimeException("please enter name correctly!");
            } catch (RuntimeException e) {
                System.out.println("Error in Logging : " + e.getMessage());
                sleepTime(1000);
                patientLogin();
            }
        }
    }

    public static void patientCenter(Patient patient) throws SQLException, ClassNotFoundException {
        clearConsole();

        System.out.println("**** welcome dear " + patient.getName() + " *****");
        System.out.println("select action from menu :");
        System.out.println("1. need examine");
        System.out.println("2. check prescriptions");
        System.out.println("3. check medications");
        num = scanner.nextInt();
        switch (num) {
            case 1:
                examine(patient);
                break;

            case 2:
                prescriptions(patient);
                break;

            case 3:
                medication(patient);
                break;
        }
    }

    public static void examine(Patient patient) throws SQLException, ClassNotFoundException {
        clearConsole();
        System.out.println("select the doctor you need:");
        for (Doctor doctor : doctors) {
            System.out.println("ID         : " + doctor.getId());
            System.out.println("name       : " + doctor.getName());
            System.out.println("speciality : " + doctor.getSpecialty());
            System.out.println("   ****************     ");
        }
        System.out.println("0. back");
        num = scanner.nextInt();
        for (Doctor doctor : doctors) {
            if (num == doctor.getId()) {
                doctor.addPatient(patient);
                examineFromDoctor(patient, doctor);
            }
        }
        if (num == 0) {
            patientCenter(patient);
        }
    }

    public static void examineFromDoctor(Patient patient, Doctor doctor) throws SQLException, ClassNotFoundException {
        System.out.println("mr/ms " + patient.getName() + "you are talking to " + doctor.getName());

//        updating illness details...
        File.updateIllness(patient, doctor.getSpecialty());

//        nurse selector
        int NursesSize = nurses.size();
        int NursesNum = new Random().nextInt(NursesSize) + 1;
        int counter = 0;
        Nurse selectedNurse = null;
        for (Nurse nurse : nurses) {
            counter++;
            if (counter == NursesNum) {
                selectedNurse = nurse;
                break;
            }
        }

//        end nusre selecting
        System.out.println("we select " + selectedNurse.getName() + " for you as nurse!");
        selectedNurse.addPatient(patient);
        System.out.println("Dr." + doctor.getName() + " select these drugs for you :");

//        filtering drugs
        ArrayList<Drug> availableDrug = drugs.stream()
                .filter(drug -> drug.isAvailable())
                .collect(Collectors.toCollection(ArrayList::new));

//        selecting drug
        int DrugSize = availableDrug.size();
        int DrugNum = new Random().nextInt(DrugSize) + 1;
        int DrugNum2 = new Random().nextInt(DrugSize) + 1;
        int Dcounter = 0;
        while (DrugNum2 == DrugNum) {
            DrugNum2 = new Random().nextInt(DrugSize) + 1;
        }
        int j = 1;
        ArrayList<Drug> drugArrayList = new ArrayList<>();

        for (Drug drug : availableDrug) {
            Dcounter++;
            if (Dcounter == DrugNum) {
                drugArrayList.add(drug);
                System.out.println(j + ". " + drug.getName());
                j++;

            } else if (Dcounter == DrugNum2) {
                drugArrayList.add(drug);
                System.out.println(j + ". " + drug.getName());
                j++;
            }
        }
//        end of selecting drug
        System.out.println("wait until we are checking availability in drugStore ...");
        sleepTime(1000);
//        loading
        boolean drugBalance = true;
        for (Drug drug : drugArrayList) {
            if (drug.isAvailable()) {
                System.out.println("well! we have this " + drug.getName());
            } else {
                try {
                    throw new RuntimeException("Oops,We don't have this " + drug.getName());
                } catch (RuntimeException e) {
                    System.out.println("Error processing drugs: " + e.getMessage());
                }
            }
        }
        if (drugBalance) {
            doctor.writePrescription(patient, drugArrayList);
            System.out.println("0. back");
            num = scanner.nextInt();
            switch (num) {
                case 0:
                    patientCenter(patient);
                    break;
            }
        }

    }

    public static void prescriptions(Patient patient) throws SQLException, ClassNotFoundException {
        System.out.println("well you want to check your prescriptions...");
        System.out.println("here is all of your prescriptions :");
        int i = 1;
        String docName = "";
        String illness = "";
        for (Patient.Prescription prescription : File.LoadPrescription()) {
            if (prescription.getPatientId() == patient.getId()) {
                System.out.println("number " + (i < 10 ? "0" + i : i) + " :");
                System.out.println("You were examined in   : " + prescription.getDate());
                for (Doctor doc : doctors) {
                    if (doc.getId() == prescription.getDoctor()) {
                        docName = doc.getName();
                        illness = doc.getSpecialty();
                    }
                }
                System.out.println("You were here for      : " + illness);
                System.out.println("You were examined by   : " + docName);
                System.out.println("Dr wrote you this drug : ");
                System.out.print("   ");
                System.out.println("1. " + prescription.drug1);
                System.out.println("    *************     ");
                System.out.print("   ");
                System.out.println("2. " + prescription.drug2);
                System.out.println("    *************     ");
            }
            i++;
        }
        System.out.println("Result Finished ...\n");
        System.out.println("0. back");
        num = scanner.nextInt();
        switch (num) {
            case 0:
                patientCenter(patient);
                break;
        }
    }

    public static void medication(Patient patient) throws SQLException, ClassNotFoundException {
        clearConsole();
        System.out.println("showing all drugs you consumed...");
        for (String drug : patient.getMedications()) {
            System.out.println("name    :  " + drug);
            System.out.println("     *******************     ");
        }
        System.out.println("");
        System.out.println("0. back");
        num = scanner.nextInt();
        switch (num) {
            case 0:
                patientCenter(patient);
                break;
        }
    }

    public static void doctorPanel(Manager manager) throws SQLException, ClassNotFoundException {
        clearConsole();
        System.out.println("**** welcome dear ****");
        System.out.println("select action from menu :");
        System.out.println("1. submit your details");
        System.out.println("2. Login to Your account");
        num = scanner.nextInt();
        switch (num) {
            case 1:
                submitDoctor(manager);
                break;
            case 2:
                doctorLogin();
                break;
        }
    }

    public static void submitDoctor(Manager manager) throws SQLException, ClassNotFoundException {
        clearConsole();
        boolean IsValid = true;
        Scanner add = new Scanner(System.in);
        System.out.println("Enter your name as userName: ");
        String name = add.nextLine();

        System.out.println("Enter your address: ");
        String address = add.nextLine();

        System.out.println("Enter your phone: ");
        String phone = add.nextLine();

        System.out.println("Enter your speciality code : ");
        System.out.println("    *********    ");
        System.out.println("1. Headache");
        System.out.println("2. Allergy");
        System.out.println("3. High blood pressure");
        System.out.println("4. Diabetes");
        System.out.println("5. Sinusitis");
        System.out.println("6. Anemia");
        String speciality = "";
        int specialityCode = 0;
        specialityCode = add.nextInt();

        switch (specialityCode) {
            case 1:
                speciality = "Headache";
                break;
            case 2:
                speciality = "Allergy";
                break;
            case 3:
                speciality = "High blood pressure";
                break;
            case 4:
                speciality = "Diabetes";
                break;
            case 5:
                speciality = "Sinusitis";
                break;
            case 6:
                speciality = "Anemia";
                break;
        }

        System.out.println("your salary is depends on your speciality");
//          checking the main doctor
        for (Doctor doctor : doctors) {
            try {
                if (name.equals(doctor.getName())) {
                    throw new RuntimeException("please select another userName!");
                }
            } catch (RuntimeException e) {
                System.out.println("Error In Taken UserName : " + e.getMessage());
                IsValid = false;
            }

        }
//        checking the draft list
        for (Doctor doctor : File.LoadDoctors()) {
            try {
                if (name.equals(doctor.getName())) {
                    throw new RuntimeException("please select another userName!");
                }
            } catch (RuntimeException e) {
                System.out.println("Error In Taken UserName : " + e.getMessage());
                IsValid = false;
            }

        }
        if (IsValid) {
            Doctor doctor = new Doctor(name, address, phone, speciality, 0);
            System.out.println("your request sent to manager and he/she will check in few hours!");
            manager.addDraftDoctor(doctor);
            doctorLogin();
        } else {
            submitDoctor(manager);
        }
    }

    public static void doctorLogin() throws ClassNotFoundException, SQLException {
        clearConsole();
        boolean IsValid = false;
        Doctor tempDoctor = null;
        System.out.println("Enter Your Name :");
        Scanner login = new Scanner(System.in);
        String name = login.nextLine();
        for (Doctor doctor : doctors) {
            if (name.equals(doctor.getName())) {
                tempDoctor = doctor;
                IsValid = true;
            }
        }
        if (IsValid) {
            doctorCenter(tempDoctor);
        } else {
            try {
                throw new RuntimeException("please enter name correctly!");
            } catch (RuntimeException e) {
                System.out.println("Error in Logging : " + e.getMessage());
                sleepTime(1000);
                patientLogin();
            }
        }
    }

    private static void doctorCenter(Doctor doctor) {

    }

    public static void sleepTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clearConsole() {
        for (int line = 0; line < 5; line++) {
            System.out.println("\n");
        }
    }
}
