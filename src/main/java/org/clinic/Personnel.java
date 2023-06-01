package org.clinic;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Personnel extends Person {
    private String task;

    public Personnel(String name, String address, String phone, long salary, String task) {
        super(name, address, phone, salary);
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public static ArrayList<Personnel> firstLoad() {
        ArrayList<Personnel> personnels = new ArrayList<>();
        personnels.add(new Personnel("asghar", "mashhad", "09152547856", 1000000, "WC"));
        return personnels;
    }

}

