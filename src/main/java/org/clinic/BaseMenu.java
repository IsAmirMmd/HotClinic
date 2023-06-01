package org.clinic;

import javax.print.Doc;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;


public class BaseMenu {
    public static int num;
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Patient> patients = Patient.firstLoad();
    public static ArrayList<Doctor> doctors = Doctor.firstLoad();
    public static ArrayList<Nurse> nurses = Nurse.firstLoad();
    public static ArrayList<Drug> drugs = Drug.firstLoad();
    public static ArrayList<Personnel> personnels = Personnel.firstLoad();
    public static Manager manager = new Manager("amirMmd", null, null, "N.I.T", patients, doctors, nurses, drugs, personnels);


    public static void baseMenu() {
        System.out.println("1. manager panel");
        System.out.println("2. patient panel");
        num = scanner.nextInt();
        switch (num) {
            case 1:
                managerLogin();
                break;
            case 2:
                patientPanel();
                break;
        }
    }

    public static void managerLogin() {
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
            System.out.println("username or pasword is wrong");
        }
    }

    public static void managerPanel(Manager manager) {
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

    public static void manageDoctor() {
        clearConsole();
        System.out.println("**** Doctor menu ****");
        System.out.println("1. Add Doctor");
        System.out.println("2. Remove Doctor");
        System.out.println("3. Show All Doctors");
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

            case 0:
                managerPanel(manager);
                break;
        }
    }

    public static void addDoctor() {
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
        doctors.add(doctor);
        manager.addDoctor(doctor);

        System.out.println("new doctor with name " + name + " added!");
        sleepTime(2000);
        manageDoctor();
    }

    public static void removeDoctor() {
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

        if (doctorToRemove == null) {
            System.out.println("Doctor not found.");
        }
        doctors.remove(doctorToRemove);
        manager.removeDoctor(doctorToRemove);

        System.out.println("the doctor with name " + name + " removed!");
        sleepTime(2000);
        manageDoctor();
    }

    public static void showAllDoctor() {
        clearConsole();
        System.out.println("showing all doctors...");
        for (Doctor doctor : doctors) {
            System.out.println("ID         :  " + doctor.getId());
            System.out.println("name       :  " + doctor.getName());
            System.out.println("address    :  " + doctor.getAddress());
            System.out.println("phone      :  " + doctor.getPhone());
            System.out.println("speciality :  " + doctor.getSpecialty());
            System.out.println("     *******************     ");
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

    public static void manageNurses() {
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

    public static void addNurse() {
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
        nurses.add(nurse);
        manager.addNurse(nurse);

        System.out.println("new nurse with name " + name + " added!");
        sleepTime(2500);
        manageNurses();
    }

    public static void removeNurse() {
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

        if (nurseToRemove == null) {
            System.out.println("Nurse not found.");
        } else {

            nurses.remove(nurseToRemove);
            manager.removeNurse(nurseToRemove);
            System.out.println("the nurse with name " + name + " removed!");
        }
        sleepTime(2500);
        manageNurses();
    }

    public static void showAllNurse() {
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


    public static void managePatient() {
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

    public static void addPatients() {
        clearConsole();
        Scanner add = new Scanner(System.in);
        System.out.println("Enter patient name: ");
        String name = add.nextLine();

        System.out.println("Enter patient address: ");
        String address = add.nextLine();

        System.out.println("Enter patient phone: ");
        String phone = add.nextLine();

        Patient patient = new Patient(name, address, phone, null);
        patients.add(patient);
        manager.addPatient(patient);

        System.out.println("new patient with name " + name + " added!");
        sleepTime(2500);
        managePatient();
    }

    public static void removePatients() {
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

        if (patientToRemove == null) {
            System.out.println("Patient not found.");
        }
        patients.remove(patientToRemove);
        manager.removePatient(patientToRemove);

        System.out.println("the patient with name " + name + " removed!");
        sleepTime(2000);
        managePatient();
    }


    public static void showAllPatients() {
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

    public static void managePersonnel() {
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

    public static void addPersonnel() {
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
        personnels.add(personnel);
        manager.addPersonnel(personnel);

        System.out.println("new personnel with name " + name + " added!");
        sleepTime(2000);
        managePersonnel();
    }

    public static void removePersonnel() {
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

        if (personnelToRemove == null) {
            System.out.println("personnel not found.");
        }
        personnels.remove(personnelToRemove);
        manager.removePersonnel(personnelToRemove);

        System.out.println("the personnel with name " + name + " removed!");
        sleepTime(2000);
        managePersonnel();
    }

    public static void showAllPersonnel() {
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

    public static void manageDrugs() {
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

    public static void addDrug() {
        clearConsole();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the drug: ");
        String name = scanner.nextLine();
        System.out.print("Enter the quantity of the drug: ");
        int quantity = scanner.nextInt();

        Drug drug = new Drug(name, quantity);

        drugs.add(drug);
        manager.addDrug(drug);

        System.out.println("new drug with name " + name + " added!");
        sleepTime(2500);
        manageDrugs();
    }

    public static void removeDrug() {
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

        if (drugToRemove == null) {
            System.out.println("Drug not found.");
        }
        drugs.remove(drugToRemove);
        manager.removeDrug(drugToRemove);

        System.out.println("the drug with name " + name + " removed!");
        sleepTime(2000);
        manageDrugs();
    }

    public static void showAllDrugs() {
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

    public static void patientPanel() {
        clearConsole();
        System.out.println("**** welcome dear ****");
        System.out.println("select action from menu :");
        System.out.println("1. submit your details");
        System.out.println("2. Login to Your account");
        num = scanner.nextInt();
        switch (num) {
            case 1:
                submitPatient();
                break;
            case 2:
                patientLogin();
                break;
        }
    }

    public static void submitPatient() {
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
            if (name.equals(patient.getName())) {
                System.out.println("please select another name!");
                IsValid = false;
            }
        }
        if (IsValid) {
            Patient patient = new Patient(name, address, phone, null);
            patients.add(patient);
        } else {
            submitPatient();
        }

    }

    public static void patientLogin() {
        clearConsole();
        boolean IsValid = false;
        Patient tempPatient = null;
        System.out.println("Enter Your Name :");
        Scanner login = new Scanner(System.in);
        for (Patient patient : patients) {
            if (login.nextLine().equals(patient.getName())) {
                tempPatient = patient;
                IsValid = true;
            }
        }
        if (IsValid) {
            patientCenter(tempPatient);
        } else {
            System.out.println("please enter name correctly!");
            sleepTime(1000);
            patientLogin();
        }
    }

    public static void patientCenter(Patient patient) {
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

    public static void examine(Patient patient) {
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

    public static void examineFromDoctor(Patient patient, Doctor doctor) {
        System.out.println("mr/ms " + patient.getName() + "you are talking to " + doctor.getName());
        patient.setIllness(doctor.getSpecialty());
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
        System.out.println("we select " + selectedNurse.getName() + " for you");
        selectedNurse.addPatient(patient);
        System.out.println("Dr." + doctor.getName() + " select these drugs for you :");
//        selecting drug
        int DrugSize = drugs.size();
        int DrugNum = new Random().nextInt(DrugSize) + 1;
        int DrugNum2 = new Random().nextInt(DrugSize) + 1;
        int Dcounter = 0;
        while (DrugNum2 == DrugNum) {
            DrugNum2 = new Random().nextInt(DrugSize) + 1;
        }
        int j = 1;
        ArrayList<Drug> drugArrayList = new ArrayList<>();
        for (Drug drug : drugs) {
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
                System.out.println("well!\nwe have this " + drug.getName());
            } else {
                System.out.println("oops,we don't have this " + drug.getName());
                drugBalance = false;
            }
        }
        if (drugBalance) {
            doctor.writePrescription(patient, drugArrayList);
        }

    }

    public static void prescriptions(Patient patient) {
        System.out.println("well you want to check your prescriptions...");
        System.out.println("here is all of your prescriptions :");
        int i = 1;
        int j = 1;
        for (Patient.Prescription prescription : patient.getPrescriptions()) {
            System.out.println("number " + i + " :");
            System.out.println("You were examined in   : " + prescription.getDate());
            System.out.println("You were examined by   : " + prescription.getDoctor().getName());
            System.out.println("Dr wrote you this drug : ");
            for (Drug drug : prescription.getMedication()) {
                System.out.print("   ");
                System.out.println(j + ". " + drug.getName());
                j++;
            }
            System.out.println("  *************     ");
        }
    }

    public static void medication(Patient patient) {
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
