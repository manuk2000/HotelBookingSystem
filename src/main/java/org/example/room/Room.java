package org.example.room;

import org.example.roomProperty.IRoomProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room implements IRoom, Serializable {
    private static int numberRoom = 0;
    private List<IRoomProperty> property;
    private double price;
    private TypeRoom typeRoom;
    private int currentNumberRoom;

    public Room(List<IRoomProperty> property, TypeRoom typeRoom) {
        this.property = property;
        this.price = takePrice(typeRoom);
        currentNumberRoom = numberRoom++;
        this.typeRoom = typeRoom;

    }

    private double takePrice(TypeRoom roomType) {
        return switch (roomType) {
            case DELUXE_ROOM -> 55;
            case DOUBLE_ROOM -> 35;
            case SINGLE_ROOM -> 20;
        };
    }

    @Override
    public TypeRoom getTypeRoom() {
        return typeRoom;
    }

    @Override
    public int getCurrentNumberRoom() {
        return currentNumberRoom;
    }

    @Override
    public List<IRoomProperty> getProperty() {
        return new ArrayList<>(property);
    }

    @Override
    public void addProperty(IRoomProperty property) {
        this.property.add(property);
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getNumberRoom() {
        return currentNumberRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Double.compare(room.price, price) == 0 && currentNumberRoom == room.currentNumberRoom && Objects.equals(property, room.property) && typeRoom == room.typeRoom;
    }

    @Override
    public int hashCode() {
        return Objects.hash(property, price, typeRoom, currentNumberRoom);
    }
}
