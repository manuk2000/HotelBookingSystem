package org.example.consumer;

public interface ICustomer {
    String getName();

    void setName(String name);

    String getEmail();

    void setEmail(String email);

    ConsumerType getConsumerType();
}
