package org.example.roomProperty;

import java.io.Serializable;

public class RoomProperty implements Serializable, IRoomProperty {
    private int quantity;
    private String name;

    public RoomProperty(int quantity, String name) {
        this.quantity = quantity;
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getName() {
        return name;
    }
}
