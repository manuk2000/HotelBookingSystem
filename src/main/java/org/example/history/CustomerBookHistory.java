package org.example.history;

import org.example.consumer.ICustomer;
import org.example.room.IRoom;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomerBookHistory {
    private ICustomer consumer;
    private Map<IRoom, Set<StartEndDate>> history;

    public CustomerBookHistory(ICustomer consumer) {
        history = new HashMap<>();
        this.consumer = consumer;
    }

    public ICustomer getConsumer() {
        return consumer;
    }

    public Map<IRoom, Set<StartEndDate>> getHistory() {
        return new HashMap<>(history);
    }

    public boolean addBookToHistory(IRoom room, StartEndDate period) {
        if (! history.containsKey(room)) {
            history.put(room , new HashSet<>());
        }
        Set<StartEndDate> set = history.get(room);
        if (set.add(period)) {
            System.out.println("Time fixed");
            return true;
        }
        System.out.println("Artie's busy this Data");
        return false;
    }

}
