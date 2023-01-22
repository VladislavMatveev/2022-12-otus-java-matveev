package homework;

import java.util.*;

public class CustomerReverseOrder {

    private final List<Customer> customers;

    public CustomerReverseOrder() {
        customers = new LinkedList<>();
    }

    public void add(Customer customer) {
        customers.add(customer);
    }

    public Customer take() {
        return customers.remove(customers.size()-1);
    }
}
