package org.example;

import org.example.consumer.ICustomer;
import org.example.history.CustomerBookHistory;
import org.example.history.RoomBookHistory;
import org.example.history.StartEndDate;
import org.example.room.IRoom;
import org.example.room.TypeRoom;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Hotel {
    private List<RoomBookHistory> roomsAndReservesHistory;
    private List<CustomerBookHistory> cutomerAndReservesHistory;

    public Hotel() {
        this.roomsAndReservesHistory = new ArrayList<>();
        this.cutomerAndReservesHistory = new ArrayList<>();
    }

    public boolean addRoom(IRoom room) {
        return roomsAndReservesHistory.add(new RoomBookHistory(room));
    }

    public void addCunsomer(ICustomer customer) {
        cutomerAndReservesHistory.add(new CustomerBookHistory(customer));
    }

    public boolean bookRoom(ICustomer customer, TypeRoom typeRoom, StartEndDate startEndDateTime) {
        Optional<RoomBookHistory> room = findRoom(typeRoom, startEndDateTime);
        if (room.isEmpty()) {
            return false;
        }


        Optional<CustomerBookHistory> foundedCustomer = findCustomer(customer);
        if (foundedCustomer.isEmpty()) {
            return false;
        }

        room.get().addBookToHistory(startEndDateTime);
        foundedCustomer.get().addBookToHistory(room.get().getRoom(), startEndDateTime);
        System.out.println(generateBill(foundedCustomer.get(), room.get(), startEndDateTime));
        return true;
    }

    private Optional<CustomerBookHistory> findCustomer(ICustomer customer) {
        Optional<CustomerBookHistory> foundedCustomer = cutomerAndReservesHistory.stream()
                .filter(arg -> arg.getConsumer().equals(customer))
                .findFirst();
        return foundedCustomer;
    }

    private Optional<RoomBookHistory> findRoom(TypeRoom typeRoom, StartEndDate startEndDateTime) {
        Optional<RoomBookHistory> first =
                roomsAndReservesHistory.stream()
                        .filter(room -> room.getRoom().getTypeRoom().equals(typeRoom))
                        .filter(arg -> arg.isOpenForBooking(startEndDateTime))
                        .findFirst();
        return first;
    }

    private String generateBill(CustomerBookHistory foundedCustomer, RoomBookHistory room, StartEndDate startEndDateTime) {
        String context = "Consumer Name: " + foundedCustomer.getConsumer().getName() + " " +
                "Email : " + foundedCustomer.getConsumer().getEmail() + " " +
                "Room Number: " + room.getRoom().getNumberRoom() + " " +
                "Room price: " + room.getRoom().getPrice() + " " +
                "Time book" + startEndDateTime.toString();
        return context;
    }
}
