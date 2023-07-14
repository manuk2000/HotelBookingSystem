package org.example.room;

import org.example.roomProperty.IRoomProperty;

import java.util.ArrayList;
import java.util.List;

public interface IRoom {
    TypeRoom getTypeRoom();

    int getCurrentNumberRoom();

    List<IRoomProperty> getProperty();

    void addProperty(IRoomProperty property);

    double getPrice();

    void setPrice(double price);

    int getNumberRoom();
}
