package org.example.room;

import org.example.roomProperty.IRoomProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface IRoom extends Serializable {
    TypeRoom getTypeRoom();

    int getCurrentNumberRoom();

    List<IRoomProperty> getProperty();

    void addProperty(IRoomProperty property);

    double getPrice();

    void setPrice(double price);

    int getNumberRoom();
}
