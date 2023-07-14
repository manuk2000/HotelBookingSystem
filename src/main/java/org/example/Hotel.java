package org.example;

import org.example.consumer.ICustomer;
import org.example.history.CustomerBookHistory;
import org.example.history.RoomBookHistory;
import org.example.history.StartEndDate;
import org.example.room.IRoom;
import org.example.room.Room;
import org.example.room.TypeRoom;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


import java.util.InputMismatchException;

public class Hotel implements Serializable {
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

    public ICustomer findCustomer(String email) {
        for (CustomerBookHistory customerHistory : cutomerAndReservesHistory) {
            if (customerHistory.getConsumer().getEmail().equals(email)) {
                return customerHistory.getConsumer();
            }
        }
        return null;
    }

    public void generateReport() {
        Scanner scanner = new Scanner(System.in);
        int roomNumber = inputRoomNumber(scanner);
        String context = report(roomNumber);
        System.out.print("Enter file name to save the report: ");
        scanner.nextLine();
        String fileName = scanner.nextLine();
        fileName += ".txt";
        System.out.println(context);
        try (Writer writer = new FileWriter(fileName)) {
            writer.write(context);
            System.out.println("Report generated successfully.");
        } catch (IOException e) {
            System.out.println("Failed to generate report.");
        }
    }

    private String report(int roomNumber) {
        String context = " ";
        for (CustomerBookHistory customerHistory : cutomerAndReservesHistory) {
            for (IRoom room : customerHistory.getHistory().keySet()) {
                if (room.getNumberRoom() == roomNumber) {
                    context = "Customer name " + customerHistory.getConsumer().getName() + " "
                            + ":Room number " + roomNumber + " " + customerHistory.getHistory().get(room).toString() + "\n";
                }
            }
        }
        return context;
    }

    private int inputRoomNumber(Scanner scanner) {
        int roomNumber;
        while (true) {
            try {
                System.out.print("Enter room number: ");
                roomNumber = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter an integer.");
            }
        }
        return roomNumber;
    }

    private String generateBill(CustomerBookHistory foundedCustomer, RoomBookHistory room, StartEndDate startEndDateTime) {
        String context = "Consumer Name: " + foundedCustomer.getConsumer().getName() + " " +
                "Email : " + foundedCustomer.getConsumer().getEmail() + " " +
                "Room Number: " + room.getRoom().getNumberRoom() + " " +
                "Room price: " + room.getRoom().getPrice() + " " +
                "Time book" + startEndDateTime.toString();
        return context;
    }

    public void serializeHotel(String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(this);
            System.out.println("Hotel object serialized and saved to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Static method to deserialize Hotel object from a file
    public static Hotel deserializeHotel(String filePath) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            Hotel hotel = (Hotel) objectIn.readObject();
            System.out.println("Hotel object deserialized from file: " + filePath);
            return hotel;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
