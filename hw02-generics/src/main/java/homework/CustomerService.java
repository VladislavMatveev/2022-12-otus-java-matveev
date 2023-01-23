package homework;

import java.util.*;

public class CustomerService {

    private final NavigableMap<Customer, String> customers;

    public CustomerService() {
        this.customers = new TreeMap<>(Comparator.comparingLong(Customer::getScores));
    }

    public Map.Entry<Customer, String> getSmallest() {
        var currentEntry = customers.firstEntry();
        return Map.entry(new Customer(currentEntry.getKey()), currentEntry.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        var currentEntry = customers.higherEntry(customer);
        return currentEntry == null ? null : Map.entry(new Customer(currentEntry.getKey()), currentEntry.getValue());
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }
}
