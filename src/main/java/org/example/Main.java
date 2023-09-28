package org.example;
import org.example.Hotel;
import org.example.consumer.Customer;
import org.example.consumer.ICustomer;
import org.example.history.StartEndDate;
import org.example.room.IRoom;
import org.example.room.Room;
import org.example.room.TypeRoom;
import org.example.roomProperty.IRoomProperty;
import org.example.roomProperty.RoomProperty;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Hotel hotel = new Hotel();

    public static void main(String[] args) {

// ... perform operations on the hotel object ...

        String filePath = "hotel.ser";


// Deserialize Hotel object from a file
        hotel = Hotel.deserializeHotel(filePath);
        if(hotel == null){
            hotel = new Hotel();
        }

        Scanner scanner = new Scanner(System.in);

        if (hotel != null) {
            int choice;

            do {
                System.out.println("1. Add a room");
                System.out.println("2. Add a customer");
                System.out.println("3. Book a room");
                System.out.println("4. Generate report for a room");
                System.out.println("0. Exit");

                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        addRoom(scanner);
                        break;
                    case 2:
                        addCustomer(scanner);
                        break;
                    case 3:
                        bookRoom(scanner);
                        break;
                    case 4:
                        hotel.generateReport();
                        break;
                    case 0:
                        hotel.serializeHotel(filePath);
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                System.out.println();
            } while (choice != 0);

            scanner.close();
        }
    }

    private static void addRoom(Scanner scanner) {
        TypeRoom typeRoom;
        while (true) {
            System.out.print("Enter room type (SINGLE_ROOM, DOUBLE_ROOM, DELUXE_ROOM): ");
            String TypeRoomStr = scanner.nextLine();
            try {
                typeRoom = TypeRoom.valueOf(TypeRoomStr);
                break;
            } catch (IllegalArgumentException e) {
            }
        }


        List<IRoomProperty> IRoomPropertyList = new ArrayList<>();
        char addIRoomPropertyChoice;
        do {
            System.out.print("Enter IRoomProperty name: ");
            String IRoomPropertyName = scanner.nextLine();

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            IRoomPropertyList.add(new RoomProperty(quantity, IRoomPropertyName));

            System.out.print("Add another IRoomProperty? (y/n): ");
            addIRoomPropertyChoice = scanner.nextLine().charAt(0);
        } while (addIRoomPropertyChoice == 'y' || addIRoomPropertyChoice == 'Y');

        IRoom room = new Room(IRoomPropertyList, typeRoom);
        hotel.addRoom(room);
        System.out.println("Room added successfully.");
    }

    private static void addCustomer(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();

        ICustomer customer = new Customer(name, email);
        hotel.addCunsomer(customer);
        System.out.println("Customer added successfully.");
    }

    private static void bookRoom(Scanner scanner) {
        System.out.print("Enter customer email: ");
        String name = scanner.nextLine();

        ICustomer customer = hotel.findCustomer(name);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        TypeRoom typeRoom;
        while (true) {
            System.out.print("Enter room type (SINGLE_ROOM, DOUBLE_ROOM, DELUXE_ROOM): ");
            String TypeRoomStr = scanner.nextLine();
            try {
                typeRoom = TypeRoom.valueOf(TypeRoomStr);
                break;
            } catch (IllegalArgumentException e) {
            }
        }

        LocalDate startDate;
        LocalDate endDate;
        while (true) {

            try {
                System.out.print("Enter start date (yyyy-mm-dd): ");
                String startDateStr = scanner.nextLine();
                startDate = LocalDate.parse(startDateStr);


                System.out.print("Enter end date (yyyy-mm-dd): ");
                String endDateStr = scanner.nextLine();
                endDate = LocalDate.parse(endDateStr);
                break;
            } catch (DateTimeException e) {
            }
        }

        StartEndDate reserveDuration;
        try {
            reserveDuration = StartEndDate.createDataTime(startDate, endDate);
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
            return;
        }


        boolean room = hotel.bookRoom(customer, typeRoom, reserveDuration);
        if (!room) {
            System.out.println("No available rooms of the specified type.");
            return;
        }

        System.out.println("Room booked successfully.");
    }

}
