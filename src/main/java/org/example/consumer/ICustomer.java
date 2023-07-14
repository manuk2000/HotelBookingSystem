package org.example.consumer;

import java.io.Serializable;

public interface ICustomer extends Serializable {
    String getName();

    void setName(String name);

    String getEmail();

    void setEmail(String email);

}
