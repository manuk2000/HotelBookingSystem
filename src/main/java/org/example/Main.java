package org.example;

import org.example.consumer.ConsumerType;
import org.example.consumer.Customer;
import org.example.consumer.ICustomer;
import org.example.history.StartEndDate;
import org.example.room.Room;
import org.example.room.TypeRoom;
import org.example.roomProperty.IRoomProperty;
import org.example.roomProperty.RoomProperty;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Hotel h = new Hotel();
        IRoomProperty prop1 = new RoomProperty(2, "prop");
        IRoomProperty prop2 = new RoomProperty(2, "prop");
        Room room = new Room(Arrays.asList(prop1, prop2), 12, TypeRoom.SINGLE_ROOM);
        h.addRoom(room);
        ICustomer consumer = new Customer("name", "email", ConsumerType.SIMPLE);
        h.addCunsomer(consumer);


        LocalDate startDate1 = LocalDate.of(2023, 7, 15);
        LocalDate endDate1 = LocalDate.of(2023, 7, 17);

        LocalDate startDate2 = LocalDate.of(2023, 7, 16);
        LocalDate endDate2 = LocalDate.of(2023, 7, 18);
        StartEndDate startEndDate1 =  StartEndDate.createDataTime(startDate1, endDate1);
        StartEndDate startEndDate2 =  StartEndDate.createDataTime(startDate2, endDate2);

        h.bookRoom(consumer , TypeRoom.SINGLE_ROOM , startEndDate1);
    }
}