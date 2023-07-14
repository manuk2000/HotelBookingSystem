package org.example.history;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

public class StartEndDate implements Serializable {
    private LocalDate startDataTime;
    private LocalDate endDataTime;

    private StartEndDate(LocalDate startDataTime, LocalDate endDataTime) {
        this.startDataTime = startDataTime;
        this.endDataTime = endDataTime;
    }

    static public StartEndDate createDataTime(LocalDate endDataTime, LocalDate startDataTime) throws DateTimeException {
        if (validDataTime(endDataTime) && validDataTime(startDataTime)) {
            return new StartEndDate(endDataTime, startDataTime);
        } else {
            throw new DateTimeException("Input date time Before Local Date Time now");
        }
    }

    public LocalDate getStartDataTime() {
        return startDataTime;
    }

    public LocalDate getEndDataTime() {
        return endDataTime;
    }

    static private boolean validDataTime(LocalDate dateTime) {
        return !dateTime.isBefore(LocalDate.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StartEndDate that = (StartEndDate) o;
        return Objects.equals(startDataTime, that.startDataTime) && Objects.equals(endDataTime, that.endDataTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDataTime, endDataTime);
    }

    @Override
    public String toString() {
        return "StartEndDate{" +
                "startDataTime=" + startDataTime +
                ", endDataTime=" + endDataTime +
                '}';
    }
}
