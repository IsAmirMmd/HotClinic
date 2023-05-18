package org.clinic;

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
}
