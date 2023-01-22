package homework;

import java.util.*;

public class CustomerService {

    private NavigableMap<Customer, String> customers;

    public CustomerService() {
        this.customers = new TreeMap<>(Comparator.comparingLong(Customer::getScores));
    }

    public Map.Entry<Customer, String> getSmallest() {
        var unmodifiableMap = getDeepCopyMap();
        return unmodifiableMap.firstEntry();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        var deepCopyMap = getDeepCopyMap();
        return deepCopyMap.higherEntry(customer);
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }

    private NavigableMap<Customer, String> getDeepCopyMap() {
        NavigableMap<Customer, String> deepCopy = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

        for (Customer key : customers.keySet()) {
            deepCopy.put(new Customer(key), customers.get(key));
        }
        return deepCopy;
    }
}
