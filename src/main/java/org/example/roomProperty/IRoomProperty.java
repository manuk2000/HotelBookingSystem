package org.example.roomProperty;

import java.io.Serializable;

public interface IRoomProperty extends Serializable {
    int getQuantity();

    String getName();

    void setQuantity(int quantity);

}
