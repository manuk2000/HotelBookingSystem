package org.example.history;

import org.example.room.IRoom;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomBookHistory implements Serializable {
    private IRoom room;
    private List<StartEndDate> startEndDateTimeList;

    public RoomBookHistory(IRoom room) {
        this.room = room;
        this.startEndDateTimeList = new ArrayList<>();
    }

    public IRoom getRoom() {
        return room;
    }

    public boolean addBookToHistory(StartEndDate startEndDateTime) {
        if (isOpenForBooking(startEndDateTime)) {
            startEndDateTimeList.add(startEndDateTime);
            return true;
        }
        System.out.println("Artie's busy this Data");
        return false;
    }

    public boolean isOpenForBooking(StartEndDate startEndDateTime) {
        if (startEndDateTimeList.isEmpty()) {
            return true;
        }
        for (StartEndDate startEndDate : startEndDateTimeList) {
            if (checkDate(startEndDate, startEndDateTime)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDate(StartEndDate current, StartEndDate input) {
        LocalDate date1St = current.getStartDataTime();
        LocalDate date1End = current.getEndDataTime();

        LocalDate date2St = input.getStartDataTime();
        LocalDate date2End = input.getEndDataTime();

        boolean check1 = date1St.isBefore(date2End) && date1End.isAfter(date2St);
        boolean check2 = date1St.isBefore(date2St) && date1End.isAfter(date2St);
        boolean check3 = date1St.isAfter(date2St) && date1St.isBefore(date2End);

        return !(check1 || check2 || check3);
    }

}
