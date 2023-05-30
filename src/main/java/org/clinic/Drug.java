package org.clinic;

import java.util.ArrayList;
import java.util.UUID;

public class Drug {
    private UUID uid;
    private String name;
    private int quantity;
    private boolean available;

    public Drug(String name, int quantity) {
        this.uid = UUID.randomUUID();
        this.name = name;
        this.quantity = quantity;
        this.available = quantity > 0;
    }

    public UUID getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.available = quantity > 0;
    }

    public static ArrayList<Drug> firstLoad() {
        ArrayList<Drug> drugs = new ArrayList<>();

        drugs.add(new Drug("Ibuprofen", 10));
        drugs.add(new Drug("Acetaminophen", 7));
        drugs.add(new Drug("Aspirin", 8));
        drugs.add(new Drug("Lisinopril", 6));
        drugs.add(new Drug("Atorvastatin", 9));
        drugs.add(new Drug("Metformin", 11));
        drugs.add(new Drug("Albuterol", 12));
        drugs.add(new Drug("Furosemide", 13));
        drugs.add(new Drug("Omeprazole", 15));
        drugs.add(new Drug("Losartan", 14));

        return drugs;
    }

}
