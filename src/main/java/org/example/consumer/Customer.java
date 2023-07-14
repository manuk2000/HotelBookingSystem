package org.example.consumer;

import java.util.Objects;

public class Customer implements ICustomer {
    private String name;
    private String email;
    private ConsumerType consumerType;

    public Customer(String name, String email, ConsumerType consumerType) {
        this.name = name;
        this.email = email;
        this.consumerType = consumerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ConsumerType getConsumerType() {
        return consumerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && consumerType == customer.consumerType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, consumerType);
    }
}
