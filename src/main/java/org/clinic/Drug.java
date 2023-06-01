package org.clinic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Random;

public class Drug {
    private Random random = new Random();
    private long uid;

    private String name;
    private int quantity;
    private boolean available;

    public Drug(String name, int quantity) {
        this.uid = random.nextInt(900000) + 100000;
        this.name = name;
        this.quantity = quantity;
        this.available = quantity > 0;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
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

    public void setAvailable(boolean available) {
        this.available = available;
    }


}
